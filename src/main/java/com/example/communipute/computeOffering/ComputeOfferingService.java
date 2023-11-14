package com.example.communipute.computeOffering;

import com.example.communipute.computeResource.ComputeResource;
import com.example.communipute.user.User;
import com.example.communipute.utils.ComputeOfferingStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.OffsetDateTime;

@Service
public class ComputeOfferingService {

    /**
     * Returns a list of ComputeOffering objects. For now, it's just dummy data.
     * @return
     */
    public List<ComputeOffering> getComputeOffering() {
        return List.of(
            new ComputeOffering(OffsetDateTime.now(),
                    ComputeOfferingStatus.AVAILABLE,
                    "description1",
                    new ComputeResource("computeResource1",
                            new User("username", "password", "email@gmail.com"))),
            new ComputeOffering(OffsetDateTime.now(),
                    ComputeOfferingStatus.UNAVAILABLE,
                    "description2",
                    new ComputeResource("computeResource1",
                            new User("username", "password", "email@gmail.com"))),
            new ComputeOffering(OffsetDateTime.now(),
                    ComputeOfferingStatus.DELETED,
                    "description3",
                    new ComputeResource("computeResource1",
                            new User("username", "password", "email@gmail.com")))
        );
    }
}
