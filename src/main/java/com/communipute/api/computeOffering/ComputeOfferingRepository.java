package com.communipute.api.computeOffering;

import com.communipute.api.utils.ComputeOfferingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputeOfferingRepository extends JpaRepository<ComputeOffering, Integer> {

    List<ComputeOffering> findByStatus(ComputeOfferingStatus status);

    List<ComputeOffering> findByComputeResourceId(Integer computeResourceId);

}
