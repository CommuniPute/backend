package com.communipute.api.transaction;

import com.communipute.api.computeOffering.ComputeOffering;
import com.communipute.api.computeOffering.ComputeOfferingRepository;
import com.communipute.api.dto.FinishTransactionDTO;
import com.communipute.api.dto.PostWorkloadDTO;
import com.communipute.api.dto.TransactionPollDTO;
import com.communipute.api.dto.UpdateTransactionDTO;
import com.communipute.api.endUser.EndUser;

import com.communipute.api.utils.ComputeOfferingStatus;
import com.communipute.api.utils.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransactionService {
    /**
     * Returns all transactions. For now, just dummy data.
     * @return
     */

    private final TransactionRepository transactionRepository;
    private final ComputeOfferingRepository computeOfferingRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              ComputeOfferingRepository computeOfferingRepository) {
        this.transactionRepository = transactionRepository;
        this.computeOfferingRepository = computeOfferingRepository;
    }

    /**
     * Returns a list of transactions
     * @return
     */
    public List<Transaction> getTransactions(EndUser user) {
        return transactionRepository.findByRequestingUser(user);
    }


    /**
     * Polls transaction - can be polled by offering user and requesting user
     * @param transactionID
     * @return
     */
    public TransactionPollDTO pollTransaction(Integer transactionID, Integer userID) {

        // If computeOfferingID is not found, throw exception
        Transaction transaction = transactionRepository.findById(transactionID)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // If the user is not the owner of the compute offering or owner of the transaction, throw exception
        if (!transaction.getRequestingUser().getId().equals(userID) &&
                !transaction.getComputeOffering().getComputeResource().getOfferingUser().getId().equals(userID)) {
            throw new RuntimeException("User " + userID + " does not own the transaction "
                    + transaction.getId());
        }

        // Return poll
        return new TransactionPollDTO(transaction.getId(),
                transaction.getWorkload(),
                transaction.getStderr(),
                transaction.getStdout(),
                transaction.getStatus(),
                transaction.getTerminatingTime());
    }


    /**
     * Posts workload
     *
     * @param workload
     * @param transactionID
     * @return
     */
    public TransactionPollDTO postWorkload(PostWorkloadDTO workload, Integer transactionID, Integer userID) {
        // If transaction is not found, throw exception
        Transaction transaction = transactionRepository.findById(transactionID)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // If the user is not the owner of the transaction, throw exception
        if (!transaction.getRequestingUser().getId().equals(userID)) {
            throw new RuntimeException("User " + userID + " does not own the transaction "
                    + transaction.getId());
        }

        transaction.setWorkload(workload.getWorkload());
        transactionRepository.save(transaction);

        // Return poll
        return new TransactionPollDTO(transaction.getId(),
                transaction.getWorkload(),
                transaction.getStderr(),
                transaction.getStdout(),
                transaction.getStatus(),
                transaction.getTerminatingTime());
    }

    /**
     * Finishes transaction
     *
     * @param transactionID
     * @param finishTransactionDTO
     * @param userID
     * @return
     */
    public TransactionPollDTO finishTransaction(Integer transactionID,
                                                FinishTransactionDTO finishTransactionDTO,
                                                Integer userID) {

        // If transaction is not found, throw exception
        Transaction transaction = transactionRepository.findById(transactionID)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // If the user is not the owner of the compute resource that's fulfilling the transaction, throw exception
        if (!transaction.getComputeOffering().getComputeResource().getOfferingUser().getId().equals(userID)) {
            throw new RuntimeException("User " + userID + " does not own the transaction "
                    + transaction.getId());
        }

        // Update the transaction
        transaction.setStderr(finishTransactionDTO.getStderr());
        transaction.setStdout(finishTransactionDTO.getStdout());
        transaction.setStatus(finishTransactionDTO.getStatus());
        transaction.setTerminatingTime(OffsetDateTime.now());
        transactionRepository.save(transaction);

        // Update compute offering to mark it as available now
        ComputeOffering computeOffering = transaction.getComputeOffering();
        computeOffering.setStatus(ComputeOfferingStatus.AVAILABLE);
        computeOffering.setCurrentTransaction(null);
        computeOfferingRepository.save(computeOffering);



        return new TransactionPollDTO(transaction.getId(),
                transaction.getWorkload(),
                transaction.getStderr(),
                transaction.getStdout(),
                transaction.getStatus(),
                transaction.getTerminatingTime());
    }

    public TransactionPollDTO updateTransaction(Integer transactionID,
                                                UpdateTransactionDTO updateTransactionDTO,
                                                Integer userID) {
        // If transaction is not found, throw exception
        Transaction transaction = transactionRepository.findById(transactionID)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // If the user is not the owner of the compute resource that's fulfilling the transaction, throw exception
        if (!transaction.getComputeOffering().getComputeResource().getOfferingUser().getId().equals(userID)) {
            throw new RuntimeException("User " + userID + " does not own the transaction "
                    + transaction.getId());
        }

        // Update the transaction
        if (updateTransactionDTO.getStderr() != null) {
            transaction.setStderr(updateTransactionDTO.getStderr());
        }
        if (updateTransactionDTO.getStdout() != null) {
            transaction.setStdout(updateTransactionDTO.getStdout());
        }
        if (updateTransactionDTO.getStatus() != null) {
            transaction.setStatus(updateTransactionDTO.getStatus());
        }
        transactionRepository.save(transaction);

        return new TransactionPollDTO(transaction.getId(),
                transaction.getWorkload(),
                transaction.getStderr(),
                transaction.getStdout(),
                transaction.getStatus(),
                transaction.getTerminatingTime());

    }

}
