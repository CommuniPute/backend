package com.communipute.api.transaction;

import com.communipute.api.dto.FinishTransactionDTO;
import com.communipute.api.dto.PostingWorkloadDTO;
import com.communipute.api.dto.TransactionPollDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    /**
     * Returns all transactions. For now, just dummy data.
     * @return
     */

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Returns a list of transactions
     * @return
     */
    public List<Transaction> getTransactions(Integer userID) {
        // TODO: Modify such that only transactions related to this userID are shown
        return transactionRepository.findAll();
    }


    // TODO: Implement. Ensure that transactionID is being validated
    public TransactionPollDTO pollTransaction(Integer transactionID) {
        return null;
    }


    public TransactionPollDTO postWorkload(PostingWorkloadDTO workload, Integer transactionID) {
        return null;
    }

    public TransactionPollDTO finishTransaction(Integer transactionID, FinishTransactionDTO finishTransactionDTO) {
        return null;
    }
}
