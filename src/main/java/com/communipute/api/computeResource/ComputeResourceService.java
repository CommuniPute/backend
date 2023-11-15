package com.communipute.api.computeResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputeResourceService {

    private final ComputeResourceRepository computeResourceRepository;

    @Autowired
    public ComputeResourceService(ComputeResourceRepository computeResourceRepository) {
        this.computeResourceRepository = computeResourceRepository;
    }

    /**
     * Returns a list of compute resources
     * @return
     */
    public List<ComputeResource> getComputeResource() {
        return computeResourceRepository.findAll();
//        return List.of(
//            new ComputeResource("computeResource1",
//                    new EndUser("username", "password", "email@gmail.com")),
//            new ComputeResource("computeResource2",
//                    new EndUser("username", "password", "email@gmail.com")),
//            new ComputeResource("computeResource3",
//                    new EndUser("username", "password", "email@gmail.com"))
//        );
    }
}
