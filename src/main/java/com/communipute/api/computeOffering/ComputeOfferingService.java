package com.communipute.api.computeOffering;

import com.communipute.api.computeResource.ComputeResource;
import com.communipute.api.computeResource.ComputeResourceRepository;
import com.communipute.api.dto.NewComputeOfferingDTO;
import com.communipute.api.dto.PollOfferingDTO;
import com.communipute.api.dto.RequestComputeDTO;
import com.communipute.api.dto.TransactionPollDTO;
import com.communipute.api.endUser.EndUser;
import com.communipute.api.transaction.Transaction;
import com.communipute.api.transaction.TransactionRepository;
import com.communipute.api.utils.ComputeOfferingStatus;
import com.communipute.api.utils.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComputeOfferingService {

    private final ComputeOfferingRepository computeOfferingRepository;
    private final ComputeResourceRepository computeResourceRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public ComputeOfferingService(ComputeOfferingRepository computeOfferingRepository,
                                  ComputeResourceRepository computeResourceRepository,
                                  TransactionRepository transactionRepository)  {
        this.computeOfferingRepository = computeOfferingRepository;
        this.computeResourceRepository = computeResourceRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Returns a list of available ComputeOffering objects
     * @return
     */
    public List<ComputeOffering> getAvailableComputeOfferings() {
        return computeOfferingRepository.findByStatus(ComputeOfferingStatus.AVAILABLE);
    }

    /**
     * Registers a new compute offering. Ensures that the user making this offering owns the compute resource
     *
     * @param newComputeOfferingDTO
     * @return
     */
    public ComputeOffering addNewComputeOffering(NewComputeOfferingDTO newComputeOfferingDTO, Integer endUserID) {

        ComputeResource computeResource = computeResourceRepository.findById(newComputeOfferingDTO.getComputeResourceID())
                .orElseThrow(() -> new RuntimeException("Compute Resource not found"));

        if (!computeResource.getOfferingUser().getId().equals(endUserID)) {
            throw new RuntimeException("User " + endUserID + " does not own the compute resource "
                    + computeResource.getId());
        }

        // Validate that the time available is in the future
        if (newComputeOfferingDTO.getTimeAvailable().isBefore(OffsetDateTime.now())) {
            throw new RuntimeException("Time available must be in the future");
        }

        ComputeOffering computeOffering = new ComputeOffering(
                newComputeOfferingDTO.getTimeAvailable(),
                ComputeOfferingStatus.AVAILABLE,
                newComputeOfferingDTO.getFilters(),
                computeResource
        );

        return computeOfferingRepository.save(computeOffering);

    }

    /**
     * Polls a compute offering to see its status
     *
     * @param computeOfferingID
     * @return
     */
    public PollOfferingDTO pollOffer(Integer computeOfferingID, Integer endUserID) {
        // If computeOfferingID is not found, throw exception
        ComputeOffering computeOffering = computeOfferingRepository.findById(computeOfferingID)
                .orElseThrow(() -> new RuntimeException("Compute Resource not found"));

        // If computeOfferingID is found, ensure user is owner of compute resource for that offering
        if (!computeOffering.getComputeResource().getOfferingUser().getId().equals(endUserID)) {
            throw new RuntimeException("User " + endUserID + " does not own the compute resource "
                    + computeOffering.getId());
        }

        // If computeOfferingID is found, check status
        switch (computeOffering.getStatus()) {
            case AVAILABLE:
                return new PollOfferingDTO(computeOfferingID, ComputeOfferingStatus.AVAILABLE);
            case DELETED:
                return new PollOfferingDTO(computeOfferingID, ComputeOfferingStatus.DELETED);
            // Returns the transaction ID if the compute offering is in use (means a transaction has been created)
            case IN_USE:
                return new PollOfferingDTO(computeOfferingID, ComputeOfferingStatus.IN_USE,
                        computeOffering.getCurrentTransaction().getId());
            default:
                throw new RuntimeException("Compute resource " + computeOffering.getId() + " has an invalid status");
        }

    }


    /**
     * Endpoint requests a particular compute for the requesting user. User must be authroized and
     * authenticated when making this request. offerID must be valid and active for a successful request.
     * Once the compute has been identified as available, a transaction will be created on behalf of the
     * user where the user will have access to the compute. The requested computeOffering object will be
     * modified to hold the current transactionID. Then transaction ID will be returned as part
     * of the success 200 message.
     *
     * Request body must contain a terminating time. The workload is optional - if not provided,
     * the workload will be null. This allows both workflows - where the requester is selected after the workload
     * being provided, and where the workload is provided after the requester is selected.
     *
     * Next step would be to hit the /computes/postWorkload/:transactionID with the appropriate workload to begin
     * execution.
     *
     * Endpoint only used by requester
     * @param computeOfferId
     * @return
     */
    public TransactionPollDTO claimComputeOffering(Integer computeOfferId,
                                                   EndUser user,
                                                   RequestComputeDTO requestComputeDTO)
            throws RuntimeException {

        ComputeOffering computeOffering = computeOfferingRepository.findById(computeOfferId)
                .orElseThrow(() -> new RuntimeException("Compute Resource not found"));

        // If computeOfferingID is found, check status
        switch (computeOffering.getStatus()) {
            case AVAILABLE: {
                // Ensure that the timing provided in requestComputeDTO terminates before computeOffering
                // is no longer available
                if (requestComputeDTO.getTerminatingTime().isAfter(computeOffering.getTimeAvailable())) {
                    throw new RuntimeException("Terminating time must be before compute offering is no longer available");
                }
                Transaction transaction = new Transaction(
                        user,
                        computeOffering,
                        requestComputeDTO.getWorkload(),
                        requestComputeDTO.getTerminatingTime(),
                        null,
                        null,
                        TransactionStatus.PENDING
                );

                // Set the current transaction for the compute offering
                transactionRepository.save(transaction);

                computeOffering.setCurrentTransaction(transaction);
                computeOffering.setStatus(ComputeOfferingStatus.IN_USE);
                computeOfferingRepository.save(computeOffering);

                return new TransactionPollDTO(transaction.getId(),
                        transaction.getWorkload(),
                        transaction.getStderr(),
                        transaction.getStdout(),
                        transaction.getStatus(),
                        transaction.getTerminatingTime());
            }
            case DELETED:
                throw new RuntimeException("Compute resource " + computeOffering.getId() + " has been deleted");
            // Returns the transaction ID if the compute offering is in use (means a transaction has been created)
            case IN_USE:
                throw new RuntimeException("Compute resource " + computeOffering.getId() + " is already in use");
            default:
                throw new RuntimeException("Compute resource " + computeOffering.getId() + " has an invalid status");
        }
    }

    /**
     * Gets all compute offerings for an end user
     * @param userId
     * @return
     */
    public List<ComputeOffering> getEndUserComputeOfferings(Integer userId) {

        List<ComputeOffering> toReturn = new ArrayList<>();

        for (ComputeResource computeResource : computeResourceRepository.findByOfferingUserId(userId)) {

            toReturn.addAll(computeOfferingRepository.findByComputeResourceId(computeResource.getId()));

        }

        return toReturn;

    }

}
