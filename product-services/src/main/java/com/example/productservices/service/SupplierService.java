package com.example.productservices.service;

import com.example.productservices.dto.SupplierRequest;
import com.example.productservices.dto.SupplierResponse;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService{

    public SupplierResponse addSupplier(SupplierRequest request);
}
