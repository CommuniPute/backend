package com.communipute.api.endUser;

import com.communipute.api.computeOffering.ComputeOffering;
import com.communipute.api.computeOffering.ComputeOfferingRepository;
import com.communipute.api.computeResource.ComputeResource;
import com.communipute.api.computeResource.ComputeResourceRepository;
import com.communipute.api.transaction.Transaction;
import com.communipute.api.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EndUserService {

    private final EndUserRepository endUserRepository;

    @Autowired
    public EndUserService(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

}
