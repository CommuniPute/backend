package com.example.communipute.computeOffering;

import com.example.communipute.computeResource.ComputeResource;
import com.example.communipute.utils.ComputeOfferingStatus;

import java.time.OffsetDateTime;

public class ComputeOffering {

    private Long id;
    private OffsetDateTime timeAvailable;
    private ComputeOfferingStatus status;
    private String filters;
    private ComputeResource computeResource;

    public ComputeOffering(Long id,
                           OffsetDateTime timeAvailable,
                           ComputeOfferingStatus status,
                           String filters,
                           ComputeResource computeResource) {
        this.id = id;
        this.timeAvailable = timeAvailable;
        this.status = status;
        this.filters = filters;
        this.computeResource = computeResource;
    }

    public ComputeOffering(OffsetDateTime timeAvailable,
                           ComputeOfferingStatus status,
                           String filters,
                           ComputeResource computeResource) {
        this.timeAvailable = timeAvailable;
        this.status = status;
        this.filters = filters;
        this.computeResource = computeResource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ComputeResource getComputeResource() {
        return computeResource;
    }

    public void setComputeResource(ComputeResource computeResource) {
        this.computeResource = computeResource;
    }

    @Override
    public String toString() {
        return "ComputeOffering{" +
                "id=" + id +
                ", timeAvailable=" + timeAvailable +
                ", status=" + status +
                ", filters='" + filters + '\'' +
                ", computeResource=" + computeResource +
                '}';
    }
}
