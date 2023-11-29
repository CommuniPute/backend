package com.communipute.api.dto;

import com.communipute.api.utils.TransactionStatus;

import java.time.OffsetDateTime;

public class TransactionPollDTO {

    private Integer transactionID;
    private String workload;
    private String stderr;
    private String stdout;
    private TransactionStatus status;
    private OffsetDateTime terminatingTime;

    public TransactionPollDTO(Integer transactionID,
                              String workload,
                              String stderr,
                              String stdout,
                              TransactionStatus status,
                              OffsetDateTime terminatingTime) {
        this.transactionID = transactionID;
        this.workload = workload;
        this.stderr = stderr;
        this.stdout = stdout;
        this.status = status;
        this.terminatingTime = terminatingTime;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public OffsetDateTime getTerminatingTime() {
        return terminatingTime;
    }

    public void setTerminatingTime(OffsetDateTime terminatingTime) {
        this.terminatingTime = terminatingTime;
    }
}
