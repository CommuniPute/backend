package com.communipute.api.transaction;

import com.communipute.api.endUser.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // Find all transactions that relate to this userID
    List<Transaction> findByRequestingUser(EndUser user);


}
