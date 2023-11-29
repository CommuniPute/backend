package com.communipute.api.transaction;

import com.communipute.api.computeOffering.ComputeOffering;
import com.communipute.api.utils.TransactionStatus;
import com.communipute.api.endUser.EndUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "transaction_id_sequence",
            sequenceName = "transaction_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_id_sequence"
    )


    private Integer id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "end_user_id_sequence", nullable = false)
    private EndUser requestingUser;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "compute_offering_id_sequence", nullable = false)
    private ComputeOffering computeOffering;
    // Consider making this its own object?
    private String workload;
    private OffsetDateTime terminatingTime;
    private String stderr;
    private String stdout;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public Transaction(Integer id,
                       EndUser requestingUser,
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

    public Transaction(EndUser requestingUser,
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

    public Transaction() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EndUser getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(EndUser requestingUser) {
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
