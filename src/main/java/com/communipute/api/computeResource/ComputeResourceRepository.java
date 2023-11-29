package com.communipute.api.computeResource;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputeResourceRepository extends JpaRepository<ComputeResource, Integer> {

    List<ComputeResource> findByOfferingUserId(Integer offeringUserId);

}
