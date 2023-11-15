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
    public List<ComputeResource> getComputeResource() {
        return computeResourceRepository.findAll();
    }

    public ComputeResource createComputeResource(ComputeResourceDTO computeResourceDTO) {
        EndUser user = endUserRepository.findById(computeResourceDTO.getOfferingUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ComputeResource computeResource = new ComputeResource(computeResourceDTO.getComputeDescription(),
                user);

        return computeResourceRepository.save(computeResource);
    }

}
