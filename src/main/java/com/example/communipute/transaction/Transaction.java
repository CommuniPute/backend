package com.example.communipute.transaction;

import com.example.communipute.computeOffering.ComputeOffering;
import com.example.communipute.user.User;
import com.example.communipute.utils.TransactionStatus;

import java.time.OffsetDateTime;

public class Transaction {

    private Long id;
    private User requestingUser;
    private ComputeOffering computeOffering;
    // Consider making this its own object?
    private String workload;
    private OffsetDateTime terminatingTime;
    private String stderr;
    private String stdout;
    private TransactionStatus status;

    public Transaction(Long id,
                       User requestingUser,
                       ComputeOffering computeOffering,
                       String workload,
                       OffsetDateTime terminatingTime,
                       String stderr,
                       String stdout,
                       TransactionStatus status) {
        this.id = id;
        this.requestingUser = requestingUser;
        this.computeOffering = computeOffering;
        this.workload = workload;
        this.terminatingTime = terminatingTime;
        this.stderr = stderr;
        this.stdout = stdout;
        this.status = status;
    }

    public Transaction(User requestingUser,
                       ComputeOffering computeOffering,
                       String workload,
                       OffsetDateTime terminatingTime,
                       String stderr,
                       String stdout,
                       TransactionStatus status) {
        this.requestingUser = requestingUser;
        this.computeOffering = computeOffering;
        this.workload = workload;
        this.terminatingTime = terminatingTime;
        this.stderr = stderr;
        this.stdout = stdout;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(User requestingUser) {
        this.requestingUser = requestingUser;
    }

    public ComputeOffering getComputeOffering() {
        return computeOffering;
    }

    public void setComputeOffering(ComputeOffering computeOffering) {
        this.computeOffering = computeOffering;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public OffsetDateTime getTerminatingTime() {
        return terminatingTime;
    }

    public void setTerminatingTime(OffsetDateTime terminatingTime) {
        this.terminatingTime = terminatingTime;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "requestingUser=" + requestingUser +
                ", computeOffering=" + computeOffering +
                ", status=" + status +
                '}';
    }
}
