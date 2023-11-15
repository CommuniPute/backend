package com.communipute.api.computeOffering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputeOfferingService {

    private final ComputeOfferingRepository computeOfferingRepository;

    @Autowired
    public ComputeOfferingService(ComputeOfferingRepository computeOfferingRepository) {
        this.computeOfferingRepository = computeOfferingRepository;
    }

    /**
     * Returns a list of ComputeOffering objects
     * @return
     */
    public List<ComputeOffering> getComputeOffering() {
        return computeOfferingRepository.findAll();
//        return List.of(
//            new ComputeOffering(OffsetDateTime.now(),
//                    ComputeOfferingStatus.AVAILABLE,
//                    "description1",
//                    new ComputeResource("computeResource1",
//                            new EndUser("username", "password", "email@gmail.com"))),
//            new ComputeOffering(OffsetDateTime.now(),
//                    ComputeOfferingStatus.UNAVAILABLE,
//                    "description2",
//                    new ComputeResource("computeResource1",
//                            new EndUser("username", "password", "email@gmail.com"))),
//            new ComputeOffering(OffsetDateTime.now(),
//                    ComputeOfferingStatus.DELETED,
//                    "description3",
//                    new ComputeResource("computeResource1",
//                            new EndUser("username", "password", "email@gmail.com")))
//        );
    }
}
