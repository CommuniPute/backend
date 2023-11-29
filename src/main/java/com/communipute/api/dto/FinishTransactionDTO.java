package com.communipute.api.dto;

import com.communipute.api.utils.TransactionStatus;

import java.time.OffsetDateTime;

public class FinishTransactionDTO {
    private String stderr;
    private String stdout;
    private TransactionStatus status;

    public FinishTransactionDTO(String stderr,
                                String stdout,
                                TransactionStatus status) {
        this.stderr = stderr;
        this.stdout = stdout;
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FinishTransactionDTO{" +
                ", stderr='" + stderr + '\'' +
                ", stdout='" + stdout + '\'' +
                ", status=" + status +
                '}';
    }
}
