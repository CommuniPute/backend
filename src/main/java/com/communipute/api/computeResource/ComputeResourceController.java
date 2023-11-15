package com.communipute.api.computeResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/computeResource")
public class ComputeResourceController {

    private final ComputeResourceService computeResource;

    @Autowired
    public ComputeResourceController(ComputeResourceService computeResource) {
        this.computeResource = computeResource;
    }

    @GetMapping
    public List<ComputeResource> getUsers() {
        return computeResource.getComputeResource();
    }

}
