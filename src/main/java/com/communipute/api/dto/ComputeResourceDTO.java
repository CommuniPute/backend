package com.communipute.api.dto;

// This is a data transfer object which will allow the decoupling of the data model from the API.
public class ComputeResourceDTO {
    private String computeDescription;
    private Integer offeringUserID;

    public ComputeResourceDTO(String computeDescription, Integer offeringUserID) {
        this.computeDescription = computeDescription;
        this.offeringUserID = offeringUserID;
    }

    public String getComputeDescription() {
        return computeDescription;
    }

    public void setComputeDescription(String computeDescription) {
        this.computeDescription = computeDescription;
    }

    public Integer getOfferingUserID() {
        return offeringUserID;
    }

    public void setOfferingUserID(Integer offeringUserID) {
        this.offeringUserID = offeringUserID;
    }
}
