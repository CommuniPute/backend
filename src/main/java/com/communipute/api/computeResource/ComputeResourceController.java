package com.communipute.api.computeResource;

import com.communipute.api.dto.ComputeResourceDTO;
import com.communipute.api.endUser.EndUser;
import com.communipute.api.exceptions.UnauthenicatedUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    /**
     * Returns a list of compute resources belonging to the current authorized user
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ComputeResourceDTO>> getComputeResources() throws UnauthenicatedUserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {
            List<ComputeResource> computeResources = computeResourceService.getComputeResource(userDetails.getId());
            return ResponseEntity.ok(computeResources.stream()
                    .map(ComputeResourceDTO::new)
                    .toList());
        } else {
            throw new UnauthenicatedUserException("User not valid");
        }
    }

    /**
     * Registers a new compute resource
     * @param computeResourceDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<ComputeResourceDTO> registerNewComputeResource(@RequestBody ComputeResourceDTO computeResourceDTO)
            throws UnauthenicatedUserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle the case where there is no authenticated user
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenicatedUserException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof EndUser userDetails) {
            ComputeResource computeResource = computeResourceService.createComputeResource(computeResourceDTO,
                    userDetails.getId());
            return ResponseEntity.ok(new ComputeResourceDTO(computeResource));
        } else {
            throw new UnauthenicatedUserException("User not EndUser");
        }
    }

}
