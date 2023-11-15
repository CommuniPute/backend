package com.communipute.api.dto;

public class PostingWorkloadDTO {

    private String workloadCode;
    private String returnInstructions;

    public PostingWorkloadDTO(String workloadCode, String returnInstructions) {
        this.workloadCode = workloadCode;
        this.returnInstructions = returnInstructions;
    }

    public String getWorkloadCode() {
        return workloadCode;
    }

    public void setWorkloadCode(String workloadCode) {
        this.workloadCode = workloadCode;
    }

    public String getReturnInstructions() {
        return returnInstructions;
    }

    public void setReturnInstructions(String returnInstructions) {
        this.returnInstructions = returnInstructions;
    }

    @Override
    public String toString() {
        return "PostingWorkloadDTO{" +
                "workloadCode='" + workloadCode + '\'' +
                ", returnInstructions='" + returnInstructions + '\'' +
                '}';
    }
}

