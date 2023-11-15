package com.communipute.api.dto;

import com.communipute.api.utils.TransactionStatus;

public class FinishTransactionDTO {

    private String workload;
    private String stderr;
    private String stdout;
    private TransactionStatus status;
    private String terminatingTime;

    public FinishTransactionDTO(String workload, String stderr, String stdout, TransactionStatus status, String terminatingTime) {
        this.workload = workload;
        this.stderr = stderr;
        this.stdout = stdout;
        this.status = status;
        this.terminatingTime = terminatingTime;
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

    public String getTerminatingTime() {
        return terminatingTime;
    }

    public void setTerminatingTime(String terminatingTime) {
        this.terminatingTime = terminatingTime;
    }

    @Override
    public String toString() {
        return "FinishTransactionDTO{" +
                "workload='" + workload + '\'' +
                ", stderr='" + stderr + '\'' +
                ", stdout='" + stdout + '\'' +
                ", status=" + status +
                ", terminatingTime='" + terminatingTime + '\'' +
                '}';
    }
}
