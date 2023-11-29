package com.communipute.api.dto;

public class PostWorkloadDTO {

    private String workload;

    public PostWorkloadDTO() {
    }

    public PostWorkloadDTO(String workload) {
        this.workload = workload;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    @Override
    public String toString() {
        return "PostWorkloadDTO{" +
                "workload='" + workload + '\'' +
                '}';
    }
}
