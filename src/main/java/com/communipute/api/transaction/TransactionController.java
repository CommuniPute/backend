package com.communipute.api.transaction;

import com.communipute.api.dto.FinishTransactionDTO;
import com.communipute.api.dto.PostingWorkloadDTO;
import com.communipute.api.dto.TransactionPollDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "{userID}")
    public List<Transaction> getTransactions(@PathVariable Integer userID) {
        return transactionService.getTransactions(userID);
    }

    // TODO: Validate whether poller has authority over the transactionID via JWT probably
    @GetMapping(path = "pollTransaction/{transactionID}")
    public ResponseEntity<TransactionPollDTO> pollTransaction(@PathVariable Integer transactionID) {

        TransactionPollDTO transactionPollDTO = transactionService.pollTransaction(transactionID);

        return ResponseEntity.ok(transactionPollDTO);
    }

    // TODO: Validate whether poster has authority over the transactionID via JWT probably
    @PostMapping(path = "postWorkload/{transactionID}")
    public ResponseEntity<TransactionPollDTO> postWorkload(@RequestBody PostingWorkloadDTO workload,
                                                           @PathVariable Integer transactionID) {
        TransactionPollDTO transactionPollDTO = transactionService.postWorkload(workload, transactionID);

        return ResponseEntity.ok(transactionPollDTO);
    }

    // TODO: Validate whether poster has authority over the transactionID via JWT probably
    @PostMapping(path = "{transactionID}")
    public ResponseEntity<TransactionPollDTO> finishTransaction(@RequestBody FinishTransactionDTO finishTransactionDTO,
                                                                @PathVariable Integer transactionID) {

        TransactionPollDTO transactionPollDTO = transactionService.finishTransaction(transactionID, finishTransactionDTO);

        return ResponseEntity.ok(transactionPollDTO);
    }

}
