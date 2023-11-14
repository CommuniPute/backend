package com.example.communipute.transaction;

import com.example.communipute.computeOffering.ComputeOffering;
import com.example.communipute.computeResource.ComputeResource;
import com.example.communipute.user.User;
import com.example.communipute.utils.ComputeOfferingStatus;
import com.example.communipute.utils.TransactionStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransactionService {
    /**
     * Returns all transactions. For now, just dummy data.
     * @return
     */
    public List<Transaction> getTransactions() {
        User requestingUser = new User("username", "password", "email@gmail.com");
        User givingUser = new User("username2", "password2", "email2@gmail.com");

        ComputeOffering computeOffering = new ComputeOffering(OffsetDateTime.now(),
                ComputeOfferingStatus.UNAVAILABLE,
                "description1",
                new ComputeResource("computeResource1",
                        givingUser));
        ComputeOffering computeOffering2 = new ComputeOffering(OffsetDateTime.now(),
                ComputeOfferingStatus.AVAILABLE,
                "description2",
                new ComputeResource("computeResource2",
                        givingUser));
        ComputeOffering computeOffering3 = new ComputeOffering(
                OffsetDateTime.now(),
                ComputeOfferingStatus.DELETED,
                "description3",
                new ComputeResource("computeResource3",
                        givingUser)
        );
        return List.of(
                new Transaction(requestingUser,
                        computeOffering,
                        "workload",
                        OffsetDateTime.now(),
                        "stderr",
                        "stdout",
                        TransactionStatus.RUNNING),
                new Transaction(requestingUser,
                        computeOffering2,
                        "workload",
                        OffsetDateTime.now(),
                        "stderr",
                        "stdout",
                        TransactionStatus.PENDING),
                new Transaction(requestingUser,
                        computeOffering3,
                        "workload",
                        OffsetDateTime.now(),
                        "stderr",
                        "stdout",
                        TransactionStatus.FAILED)
        );
    }
}
