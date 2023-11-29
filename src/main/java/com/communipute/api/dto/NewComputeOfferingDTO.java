package com.communipute.api.dto;

import java.time.OffsetDateTime;

public class NewComputeOfferingDTO {

    private OffsetDateTime timeAvailable;
    private String filters;
    private Integer computeResourceID;

    public NewComputeOfferingDTO() {
    }

    public NewComputeOfferingDTO(OffsetDateTime timeAvailable,
                                 String filters,
                                 Integer computeResourceID) {
        this.timeAvailable = timeAvailable;
        this.filters = filters;
        this.computeResourceID = computeResourceID;
    }

    public OffsetDateTime getTimeAvailable() {
        return timeAvailable;
    }

    public void setTimeAvailable(OffsetDateTime timeAvailable) {
        this.timeAvailable = timeAvailable;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Integer getComputeResourceID() {
        return computeResourceID;
    }

    public void setComputeResourceID(Integer computeResourceID) {
        this.computeResourceID = computeResourceID;
    }

    @Override
    public String toString() {
        return "ComputeOfferingDTO{" +
                "timeAvailable=" + timeAvailable +
                ", filters='" + filters + '\'' +
                ", computeResourceID=" + computeResourceID +
                '}';
    }
}
