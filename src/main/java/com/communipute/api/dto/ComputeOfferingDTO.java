package com.communipute.api.dto;

import com.communipute.api.utils.ComputeOfferingStatus;

import java.time.OffsetDateTime;

public class ComputeOfferingDTO {

    private OffsetDateTime timeAvailable;
    private ComputeOfferingStatus status;
    private String filters;
    private Integer computeResourceID;

    // TODO: Perhaps consider not accepting a status and just declaring ourselves available when first conceived
    public ComputeOfferingDTO(OffsetDateTime timeAvailable,
                              ComputeOfferingStatus status,
                              String filters,
                              Integer computeResourceID) {
        this.timeAvailable = timeAvailable;
        this.status = status;
        this.filters = filters;
        this.computeResourceID = computeResourceID;
    }

    public OffsetDateTime getTimeAvailable() {
        return timeAvailable;
    }

    public void setTimeAvailable(OffsetDateTime timeAvailable) {
        this.timeAvailable = timeAvailable;
    }

    public ComputeOfferingStatus getStatus() {
        return status;
    }

    public void setStatus(ComputeOfferingStatus status) {
        this.status = status;
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
                ", status=" + status +
                ", filters='" + filters + '\'' +
                ", computeResourceID=" + computeResourceID +
                '}';
    }
}
