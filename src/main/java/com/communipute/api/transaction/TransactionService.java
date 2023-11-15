package com.communipute.api.transaction;

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
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
//        EndUser requestingUser = new EndUser("username", "password", "email@gmail.com");
//        EndUser givingUser = new EndUser("username2", "password2", "email2@gmail.com");
//
//        ComputeOffering computeOffering = new ComputeOffering(OffsetDateTime.now(),
//                ComputeOfferingStatus.UNAVAILABLE,
//                "description1",
//                new ComputeResource("computeResource1",
//                        givingUser));
//        ComputeOffering computeOffering2 = new ComputeOffering(OffsetDateTime.now(),
//                ComputeOfferingStatus.AVAILABLE,
//                "description2",
//                new ComputeResource("computeResource2",
//                        givingUser));
//        ComputeOffering computeOffering3 = new ComputeOffering(
//                OffsetDateTime.now(),
//                ComputeOfferingStatus.DELETED,
//                "description3",
//                new ComputeResource("computeResource3",
//                        givingUser)
//        );
//        return List.of(
//                new Transaction(requestingUser,
//                        computeOffering,
//                        "workload",
//                        OffsetDateTime.now(),
//                        "stderr",
//                        "stdout",
//                        TransactionStatus.RUNNING),
//                new Transaction(requestingUser,
//                        computeOffering2,
//                        "workload",
//                        OffsetDateTime.now(),
//                        "stderr",
//                        "stdout",
//                        TransactionStatus.PENDING),
//                new Transaction(requestingUser,
//                        computeOffering3,
//                        "workload",
//                        OffsetDateTime.now(),
//                        "stderr",
//                        "stdout",
//                        TransactionStatus.FAILED)
//        );
    }
}
