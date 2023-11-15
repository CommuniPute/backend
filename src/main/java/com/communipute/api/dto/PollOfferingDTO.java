package com.communipute.api.dto;

import com.communipute.api.utils.ComputeOfferingStatus;

public class PollOfferingDTO {

    private Integer computeOfferingID;
    private ComputeOfferingStatus status;
    private Integer transactionID;

    public PollOfferingDTO(Integer computeOfferingID, ComputeOfferingStatus status) {
        this.computeOfferingID = computeOfferingID;
        this.status = status;
        this.transactionID = null;
    }

    public PollOfferingDTO(Integer computeOfferingID, ComputeOfferingStatus status, Integer transactionID) {
        this.computeOfferingID = computeOfferingID;
        this.status = status;
        this.transactionID = transactionID;
    }

    public Integer getComputeOfferingID() {
        return computeOfferingID;
    }

    public void setComputeOfferingID(Integer computeOfferingID) {
        this.computeOfferingID = computeOfferingID;
    }

    public ComputeOfferingStatus getStatus() {
        return status;
    }

    public void setStatus(ComputeOfferingStatus status) {
        this.status = status;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    @Override
    public String toString() {
        return "PollOfferingDTO{" +
                "computeOfferingID=" + computeOfferingID +
                ", status=" + status +
                ", transactionID=" + transactionID +
                '}';
    }
}
