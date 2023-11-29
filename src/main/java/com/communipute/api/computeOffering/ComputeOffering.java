package com.communipute.api.computeOffering;

import com.communipute.api.computeResource.ComputeResource;
import com.communipute.api.transaction.Transaction;
import com.communipute.api.utils.ComputeOfferingStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class ComputeOffering {

    @Id
    @SequenceGenerator(
            name = "compute_offering_id_sequence",
            sequenceName = "compute_offering_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "compute_offering_id_sequence"
    )

    private Integer id;
    private OffsetDateTime timeAvailable;
    @Enumerated(EnumType.STRING)
    private ComputeOfferingStatus status;
    private String filters;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "compute_resource_id_sequence", nullable = false)
    private ComputeResource computeResource;

    @OneToOne
    @JoinColumn(name = "transaction_id_sequence", nullable = true)
    private Transaction currentTransaction;

    @JsonManagedReference
    @OneToMany(mappedBy = "computeOffering")
    private List<Transaction> transactions;

    public ComputeOffering(Integer id,
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

    public ComputeOffering() {

    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
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
