package com.communipute.api.computeOffering;

import com.communipute.api.dto.*;
import com.communipute.api.endUser.EndUser;
import com.communipute.api.exceptions.UnauthenicatedUserException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "computeOffering")
public class ComputeOfferingController {

    private static final Logger logger = LoggerFactory.getLogger(ComputeOfferingController.class);
    private final ComputeOfferingService computeOfferingService;

    @Autowired
    public ComputeOfferingController(ComputeOfferingService computeOfferingService) {
        this.computeOfferingService = computeOfferingService;
    }

    /**
     * Gets all compute offerings that are available
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ComputeOfferingDTO>> getAvailableComputeOfferings() {
        List<ComputeOffering> listOfComputeOfferings = computeOfferingService.getAvailableComputeOfferings();
        return ResponseEntity.ok(listOfComputeOfferings.stream()
                .map(ComputeOfferingDTO::new)
                .collect(Collectors.toList()));
    }

    /**
     * Registers a new compute offering - ensures that the user making this offering owns the compute resource
     * @param newComputeOfferingDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<ComputeOfferingDTO> registerNewComputeOffering(@RequestBody NewComputeOfferingDTO newComputeOfferingDTO)
            throws UnauthenicatedUserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {
            try {
                ComputeOffering computeOffering = computeOfferingService.addNewComputeOffering(newComputeOfferingDTO,
                        userDetails.getId());

                return ResponseEntity.ok(new ComputeOfferingDTO(computeOffering));
            } catch (RuntimeException e) {
                logger.error("Error registering new compute offering: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    /**
     * Once an offer has been created, use the offerID received in the success 200 message to poll for a new workload.
     * If status of our offer turns to "inUse", a transaction ID will also be returned as part of the return object.
     * Use the transactionID to poll the workload for the transaction using /computes/pollTransaction/:transactionID
     * Endpoint only used by offerer.
     */
    @PostMapping(path = "pollOffer/{computeOfferId}")
    public ResponseEntity<PollOfferingDTO> pollOffer(@PathVariable("computeOfferId") Integer computeOfferingID)
            throws UnauthenicatedUserException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {
            try {
                PollOfferingDTO pollOfferingDTO = computeOfferingService.pollOffer(computeOfferingID,
                        userDetails.getId());

                return ResponseEntity.ok(pollOfferingDTO);

            } catch (RuntimeException e) {
                logger.error("Error polling offering: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * This endpoint requests a particular compute offer. It is used by the requester. The requester must provide
     * a terminating time. The workload is optional - if not provided, the workload will be null.
     *
     * @param computeOfferId
     * @param requestComputeDTO
     * @return
     * @throws UnauthenicatedUserException
     */
    @PutMapping(path = "request/{computeOfferId}")
    public ResponseEntity<TransactionPollDTO> claimComputeOffering(@PathVariable("computeOfferId") Integer computeOfferId,
                                                                   @RequestBody RequestComputeDTO requestComputeDTO)
            throws UnauthenicatedUserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {
            try {
                TransactionPollDTO transaction = computeOfferingService.claimComputeOffering(computeOfferId,
                        userDetails, requestComputeDTO);

                return ResponseEntity.ok(transaction);
            } catch (RuntimeException e) {
                logger.error("Error requesting compute offer: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Get all computeOfferings for this user
     *
     * @return
     * @throws UnauthenicatedUserException
     */
    @GetMapping(path = "myOfferings")
    public ResponseEntity<List<ComputeOfferingDTO>> getEndUserComputeOfferings() throws UnauthenicatedUserException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {
            List<ComputeOffering> listOfComputeOfferings = computeOfferingService.getEndUserComputeOfferings(userDetails.getId());
            return ResponseEntity.ok(listOfComputeOfferings.stream()
                    .map(ComputeOfferingDTO::new)
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
