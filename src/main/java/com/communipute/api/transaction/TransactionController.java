package com.communipute.api.transaction;

import com.communipute.api.dto.*;
import com.communipute.api.endUser.EndUser;
import com.communipute.api.exceptions.UnauthenicatedUserException;

import org.hibernate.sql.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getTransactions() throws UnauthenicatedUserException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {
            List<Transaction> transactions = transactionService.getTransactions(userDetails);
            return ResponseEntity.ok(transactions.stream()
                    .map(TransactionDTO::new)
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Polls transaction
     *
     * @param transactionID
     * @return
     * @throws UnauthenicatedUserException
     */
    @GetMapping(path = "pollTransaction/{transactionID}")
    public ResponseEntity<TransactionPollDTO> pollTransaction(@PathVariable Integer transactionID)
            throws UnauthenicatedUserException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {

            try {
                TransactionPollDTO transactionPollDTO = transactionService.pollTransaction(transactionID,
                        userDetails.getId());
                return ResponseEntity.ok(transactionPollDTO);
            } catch (RuntimeException e) {
                logger.error("Transaction poll failed with: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    /**
     * Posts workload to transaction
     *
     * @param workload
     * @param transactionID
     * @return
     */
    @PostMapping(path = "postWorkload/{transactionID}")
    public ResponseEntity<TransactionPollDTO> postWorkload(@RequestBody PostWorkloadDTO workload,
                                                           @PathVariable Integer transactionID)
            throws UnauthenicatedUserException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {

            try {
                TransactionPollDTO transactionPollDTO = transactionService.postWorkload(workload, transactionID,
                        userDetails.getId());

                return ResponseEntity.ok(transactionPollDTO);

            } catch (RuntimeException e) {
                logger.error("Workload posting failed with: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    /**
     * Finishes transaction. Hit by the compute resource
     *
     * @param finishTransactionDTO
     * @param transactionID
     * @return
     */
    @PostMapping(path = "finishTransaction/{transactionID}")
    public ResponseEntity<TransactionPollDTO> finishTransaction(@RequestBody FinishTransactionDTO finishTransactionDTO,
                                                                @PathVariable Integer transactionID)
            throws UnauthenicatedUserException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {

            try {
                TransactionPollDTO transactionPollDTO = transactionService.finishTransaction(transactionID,
                        finishTransactionDTO, userDetails.getId());

                return ResponseEntity.ok(transactionPollDTO);

            } catch (RuntimeException e) {
                logger.error("Finishing transaction failed with: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Allows compute offerer to update transaction
     *
     * @param transactionID
     * @param updateTransactionDTO
     * @return
     * @throws UnauthenicatedUserException
     */
    @PostMapping(path = "updateTransaction/{transactionID}")
    public ResponseEntity<TransactionPollDTO> updateTransaction(@PathVariable Integer transactionID,
                                                                @RequestBody UpdateTransactionDTO updateTransactionDTO)
            throws UnauthenicatedUserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {
            try {
                TransactionPollDTO transactionPollDTO = transactionService.updateTransaction(transactionID,
                        updateTransactionDTO,
                        userDetails.getId());
                return ResponseEntity.ok(transactionPollDTO);
            } catch (RuntimeException e) {
                logger.error("Updating transaction failed with: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
