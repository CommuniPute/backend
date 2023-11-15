package com.communipute.api.computeOffering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/computeOffering")
public class ComputeOfferingController {

    private final ComputeOfferingService computeOfferingService;

    @Autowired
    public ComputeOfferingController(ComputeOfferingService computeOfferingService) {
        this.computeOfferingService = computeOfferingService;
    }

    @GetMapping
    public List<ComputeOffering> getUsers() {
        return computeOfferingService.getComputeOffering();
    }

}
