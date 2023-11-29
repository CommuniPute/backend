package com.communipute.api.dto;

import com.communipute.api.computeOffering.ComputeOffering;
import com.communipute.api.utils.ComputeOfferingStatus;

import java.time.OffsetDateTime;

public class ComputeOfferingDTO {

    private Integer id;
    private OffsetDateTime timeAvailable;
    private ComputeOfferingStatus status;
    private String filters;
    private Integer computeResourceID;
    private Integer currentTransactionID;

    public ComputeOfferingDTO() {
    }

    public ComputeOfferingDTO(Integer id,
                              OffsetDateTime timeAvailable,
                              ComputeOfferingStatus status,
                              String filters,
                              Integer computeResourceID,
                              Integer currentTransactionID) {
        this.id = id;
        this.timeAvailable = timeAvailable;
        this.status = status;
        this.filters = filters;
        this.computeResourceID = computeResourceID;
        this.currentTransactionID = currentTransactionID;
    }

    public ComputeOfferingDTO(ComputeOffering computeOffering) {
        this.id = computeOffering.getId();
        this.timeAvailable = computeOffering.getTimeAvailable();
        this.status = computeOffering.getStatus();
        this.filters = computeOffering.getFilters();
        this.computeResourceID = computeOffering.getComputeResource().getId();

        if (computeOffering.getCurrentTransaction() != null)
            this.currentTransactionID = computeOffering.getCurrentTransaction().getId();
        else
            this.currentTransactionID = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCurrentTransactionID() {
        return currentTransactionID;
    }

    public void setCurrentTransactionID(Integer currentTransactionID) {
        this.currentTransactionID = currentTransactionID;
    }

    @Override
    public String toString() {
        return "ComputeOfferingDTO{" +
                "id=" + id +
                ", timeAvailable='" + timeAvailable + '\'' +
                ", status='" + status + '\'' +
                ", filters='" + filters + '\'' +
                ", computeResourceID=" + computeResourceID +
                ", currentTransactionID=" + currentTransactionID +
                '}';
    }
}
