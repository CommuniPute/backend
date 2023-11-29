package com.communipute.api.dto;


import java.time.OffsetDateTime;

public class RequestComputeDTO {

    private String workload;
    private OffsetDateTime terminatingTime;

    public RequestComputeDTO() {
    }

    public RequestComputeDTO(OffsetDateTime terminatingTime) {
        this.terminatingTime = terminatingTime;
        this.workload = null;
    }

    public RequestComputeDTO(String workload, OffsetDateTime terminatingTime) {
        this.workload = workload;
        this.terminatingTime = terminatingTime;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public OffsetDateTime getTerminatingTime() {
        return terminatingTime;
    }

    public void setTerminatingTime(OffsetDateTime terminatingTime) {
        this.terminatingTime = terminatingTime;
    }

    @Override
    public String toString() {
        return "RequestComputeDTO{" +
                "workload='" + workload + '\'' +
                ", terminatingTime='" + terminatingTime + '\'' +
                '}';
    }
}
