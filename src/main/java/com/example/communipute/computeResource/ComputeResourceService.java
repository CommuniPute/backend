package com.example.communipute.computeResource;

import com.example.communipute.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputeResourceService {
    /**
     * Returns a list of compute resources. For now, it's dummy data.
     * @return
     */
    public List<ComputeResource> getComputeResource() {
        return List.of(
            new ComputeResource("computeResource1",
                    new User("username", "password", "email@gmail.com")),
            new ComputeResource("computeResource2",
                    new User("username", "password", "email@gmail.com")),
            new ComputeResource("computeResource3",
                    new User("username", "password", "email@gmail.com"))
        );
    }
}
