package com.example.communipute.computeResource;

import com.example.communipute.user.User;

public class ComputeResource {

    private Long id;
    private String computeDescription;
    private User offeringUser;

    public ComputeResource(Long id, String computeDescription, User offeringUser) {
        this.id = id;
        this.computeDescription = computeDescription;
        this.offeringUser = offeringUser;
    }

    public ComputeResource(String computeDescription, User offeringUser) {
        this.computeDescription = computeDescription;
        this.offeringUser = offeringUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComputeDescription() {
        return computeDescription;
    }

    public void setComputeDescription(String computeDescription) {
        this.computeDescription = computeDescription;
    }

    public User getOfferingUser() {
        return offeringUser;
    }

    public void setOfferingUser(User offeringUser) {
        this.offeringUser = offeringUser;
    }

    @Override
    public String toString() {
        return "ComputeResource{" +
                "id=" + id +
                ", computeDescription='" + computeDescription + '\'' +
                ", offeringUser=" + offeringUser +
                '}';
    }
}
