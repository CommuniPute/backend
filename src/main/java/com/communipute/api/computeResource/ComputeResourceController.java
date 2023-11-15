package com.communipute.api.computeResource;

import com.communipute.api.dto.ComputeResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "computeResource")
public class ComputeResourceController {

    private final ComputeResourceService computeResourceService;

    @Autowired
    public ComputeResourceController(ComputeResourceService computeResource) {
        this.computeResourceService = computeResource;
    }

    @GetMapping
    public List<ComputeResource> getComputeResources() {
        return computeResourceService.getComputeResource();
    }

    @PostMapping
    public ResponseEntity<ComputeResource> registerNewComputeResource(@RequestBody ComputeResourceDTO computeResourceDTO) {
        ComputeResource computeResource = computeResourceService.createComputeResource(computeResourceDTO);
        return ResponseEntity.ok(computeResource);
    }

}
