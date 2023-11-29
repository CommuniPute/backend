package com.communipute.api.computeResource;

import com.communipute.api.dto.ComputeResourceDTO;
import com.communipute.api.endUser.EndUser;
import com.communipute.api.endUser.EndUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputeResourceService {

    private final ComputeResourceRepository computeResourceRepository;
    private final EndUserRepository endUserRepository;

    @Autowired
    public ComputeResourceService(ComputeResourceRepository computeResourceRepository,
                                  EndUserRepository endUserRepository) {
        this.computeResourceRepository = computeResourceRepository;
        this.endUserRepository = endUserRepository;
    }

    /**
     * Returns a list of compute resources
     * @return
     */
    public List<ComputeResource> getComputeResource(Integer userId) {
        return computeResourceRepository.findByOfferingUserId(userId);
    }

    /**
     * Creates a new compute resource
     * @param computeResourceDTO
     * @param userId
     * @return
     */
    public ComputeResource createComputeResource(ComputeResourceDTO computeResourceDTO, Integer userId) {
        // Get user object corresponding to the userId
        EndUser user = endUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Gotten to createComputeResource part");
        // Create compute resource and save it
        ComputeResource computeResource = new ComputeResource(computeResourceDTO.getComputeDescription(),
                user);

        return computeResourceRepository.save(computeResource);
    }

}
