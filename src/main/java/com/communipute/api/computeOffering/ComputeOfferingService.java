package com.communipute.api.computeOffering;

import com.communipute.api.computeResource.ComputeResource;
import com.communipute.api.computeResource.ComputeResourceRepository;
import com.communipute.api.dto.ComputeOfferingDTO;
import com.communipute.api.dto.PollOfferingDTO;
import com.communipute.api.utils.ComputeOfferingStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ComputeOfferingService {

    private final ComputeOfferingRepository computeOfferingRepository;
    private final ComputeResourceRepository computeResourceRepository;

    @Autowired
    public ComputeOfferingService(ComputeOfferingRepository computeOfferingRepository, ComputeResourceRepository computeResourceRepository) {
        this.computeOfferingRepository = computeOfferingRepository;
        this.computeResourceRepository = computeResourceRepository;
    }

    /**
     * Returns a list of ComputeOffering objects
     * @return
     */
    public List<ComputeOffering> getComputeOfferings() {
        return computeOfferingRepository.findAll();
    }

    public ComputeOffering addNewComputeOffering(ComputeOfferingDTO computeOfferingDTO) {

        System.out.println("ComputeOfferingDTO: " + computeOfferingDTO);
        ComputeResource computeResource = computeResourceRepository.findById(computeOfferingDTO.getComputeResourceID())
                .orElseThrow(() -> new RuntimeException("Compute Resource not found"));

        System.out.println(computeResource);

        ComputeOffering computeOffering = new ComputeOffering(
                computeOfferingDTO.getTimeAvailable(),
                computeOfferingDTO.getStatus(),
                computeOfferingDTO.getFilters(),
                computeResource
        );

        return computeOfferingRepository.save(computeOffering);

    }

    // TODO: Implement
    public PollOfferingDTO pollOffer(Integer computeOfferingID) {
        // If computeOfferingID is not found, throw exception

        // If computeOfferingID is found, check status

        // If status is "inUse", create the appropriate response object

        // If status is "available", create the appropriate response object

        // If status is "unavailable", throw error

        return null;
    }

    // TODO: Implement
    public ComputeOffering claimComputeOffering(Integer computeOfferId, Integer endUserID) {

        // If computeOfferingID is not found, throw exception

        // If user is not found, throw exception

        // If computeOfferingID is found, check status

        // If status is "inUse", return invalid

        // If status is "available", change status to "inUse" for given user and return computeOffering

        return null;
    }
}
