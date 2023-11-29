package com.communipute.api.dto;

import com.communipute.api.transaction.Transaction;
import com.communipute.api.utils.TransactionStatus;

public class TransactionDTO {

    private Integer id;
    private Integer computeOfferingId;
    private String workload;
    private String terminatingTime;
    private String stderr;
    private String stdout;
    private TransactionStatus status;

    public TransactionDTO() {

    }

    public TransactionDTO(Integer id,
                          Integer computeOfferingId,
                          String workload,
                          String terminatingTime,
                          String stderr,
                          String stdout,
                          TransactionStatus status) {
        this.id = id;
        this.computeOfferingId = computeOfferingId;
        this.workload = workload;
        this.terminatingTime = terminatingTime;
        this.stderr = stderr;
        this.stdout = stdout;
        this.status = status;
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.computeOfferingId = transaction.getComputeOffering().getId();
        this.workload = transaction.getWorkload();
        this.terminatingTime = transaction.getTerminatingTime().toString();
        this.stderr = transaction.getStderr();
        this.stdout = transaction.getStdout();
        this.status = transaction.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComputeOfferingId() {
        return computeOfferingId;
    }

    public void setComputeOfferingId(Integer computeOfferingId) {
        this.computeOfferingId = computeOfferingId;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public String getTerminatingTime() {
        return terminatingTime;
    }

    public void setTerminatingTime(String terminatingTime) {
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
        return "TransactionDTO{" +
                "id=" + id +
                ", computeOfferingId=" + computeOfferingId +
                ", workload='" + workload + '\'' +
                ", terminatingTime='" + terminatingTime + '\'' +
                ", stderr='" + stderr + '\'' +
                ", stdout='" + stdout + '\'' +
                ", status=" + status +
                '}';
    }
}
