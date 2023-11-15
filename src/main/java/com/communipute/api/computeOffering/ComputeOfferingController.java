package com.communipute.api.computeOffering;

import com.communipute.api.dto.ComputeOfferingDTO;
import com.communipute.api.dto.PollOfferingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "computeOffering")
public class ComputeOfferingController {

    private final ComputeOfferingService computeOfferingService;

    @Autowired
    public ComputeOfferingController(ComputeOfferingService computeOfferingService) {
        this.computeOfferingService = computeOfferingService;
    }

    @GetMapping
    public List<ComputeOffering> getComputeOfferings() {
        return computeOfferingService.getComputeOfferings();
    }

    @PostMapping
    public ResponseEntity<ComputeOffering> registerNewComputeOffering(@RequestBody ComputeOfferingDTO computeOfferingDTO) {
        ComputeOffering computeOffering = computeOfferingService.addNewComputeOffering(computeOfferingDTO);
        return ResponseEntity.ok(computeOffering);
    }

    // TODO: Instead of returning a compute offering, or expecting full computeofferingDTO, create a new DTO
    /**
     * Once an offer has been created, use the offerID received in the success 200 message to poll for a new workload.
     * If status of our offer turns to "inUse", a transaction ID will also be returned as part of the return object.
     * Use the transactionID to poll the workload for the transaction using /computes/pollTransaction/:transactionID
     * Endpoint only used by offerer.
     */
    @PostMapping(path = "pollOffer/{computeOfferId}")
    public ResponseEntity<PollOfferingDTO> pollOffer(@PathVariable("computeOfferId") Integer computeOfferingID) {
        PollOfferingDTO computeOffering = computeOfferingService.pollOffer(computeOfferingID);
        return ResponseEntity.ok(computeOffering);
    }

    // TODO: Instead of returning a compute offering, create a new DTO
    @PutMapping(path = "{computeOfferId}")
    public ResponseEntity<ComputeOffering> claimComputeOffering(@PathVariable("computeOfferId") Integer computeOfferId,
                                                                Integer endUserId) {
        ComputeOffering computeOffering = computeOfferingService.claimComputeOffering(computeOfferId, endUserId);
        return ResponseEntity.ok(computeOffering);
    }

}
