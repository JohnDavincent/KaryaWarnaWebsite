package com.example.productservices.service;

import com.example.productservices.dto.SupplierCreateRequest;
import com.example.productservices.dto.SupplierResponse;
import com.example.productservices.dto.SupplierUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService{

    public SupplierResponse addSupplier(SupplierCreateRequest request);
    public SupplierResponse updateSupplier(Long id, SupplierUpdateRequest request);
    public void deleteSupplier(Long id);
}
