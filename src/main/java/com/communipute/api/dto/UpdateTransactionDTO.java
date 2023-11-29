package com.communipute.api.dto;

import com.communipute.api.utils.TransactionStatus;

public class UpdateTransactionDTO {

    private TransactionStatus status;
    private String stderr;
    private String stdout;

    public UpdateTransactionDTO() {
    }

    public UpdateTransactionDTO(TransactionStatus status, String stderr, String stdout) {
        this.status = status;
        this.stderr = stderr;
        this.stdout = stdout;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "UpdateTransactionDTO{" +
                "status=" + status +
                ", stderr='" + stderr + '\'' +
                ", stdout='" + stdout + '\'' +
                '}';
    }
}
