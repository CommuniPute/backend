package com.communipute.api.dto;

import com.communipute.api.computeResource.ComputeResource;

// This is a data transfer object which will allow the decoupling of the data model from the API.
public class ComputeResourceDTO {
    private String computeDescription;
    private Integer offeringUserId;

    public ComputeResourceDTO() {
    }

    public ComputeResourceDTO(String computeDescription) {
        this.computeDescription = computeDescription;
    }

    public ComputeResourceDTO(ComputeResource computeResource) {
        this.computeDescription = computeResource.getComputeDescription();
        this.offeringUserId = computeResource.getOfferingUser().getId();
    }

    public String getComputeDescription() {
        return computeDescription;
    }

    public void setComputeDescription(String computeDescription) {
        this.computeDescription = computeDescription;
    }

    public Integer getOfferingUserId() {
        return offeringUserId;
    }

    public void setOfferingUserId(Integer offeringUserId) {
        this.offeringUserId = offeringUserId;
    }

    @Override
    public String toString() {
        return "ComputeResourceDTO{" +
                "computeDescription='" + computeDescription + '\'' +
                ", offeringUserId=" + offeringUserId +
                '}';
    }
}
