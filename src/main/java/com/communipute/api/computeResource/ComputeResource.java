package com.communipute.api.computeResource;

import com.communipute.api.computeOffering.ComputeOffering;
import com.communipute.api.endUser.EndUser;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ComputeResource {

    @Id
    @SequenceGenerator(
            name = "compute_resource_id_sequence",
            sequenceName = "compute_resource_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "compute_resource_id_sequence"
    )

    private Integer id;
    private String computeDescription;
    @ManyToOne
    @JoinColumn(name = "end_user_id_sequence", nullable = false)
    private EndUser offeringUser;

    @OneToMany(mappedBy = "computeResource")
    private List<ComputeOffering> computeOfferings;

    public ComputeResource(Integer id, String computeDescription, EndUser offeringUser) {
        this.id = id;
        this.computeDescription = computeDescription;
        this.offeringUser = offeringUser;
    }

    public ComputeResource(String computeDescription, EndUser offeringUser) {
        this.computeDescription = computeDescription;
        this.offeringUser = offeringUser;
    }

    public ComputeResource() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComputeDescription() {
        return computeDescription;
    }

    public void setComputeDescription(String computeDescription) {
        this.computeDescription = computeDescription;
    }

    public EndUser getOfferingUser() {
        return offeringUser;
    }

    public void setOfferingUser(EndUser offeringUser) {
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
