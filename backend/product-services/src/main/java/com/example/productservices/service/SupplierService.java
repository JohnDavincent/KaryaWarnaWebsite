package com.example.productservices.service;

import com.example.productservices.dto.supplier.SupplierCreateRequest;
import com.example.productservices.dto.supplier.SupplierResponse;
import com.example.productservices.dto.supplier.SupplierUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public interface SupplierService{

    public SupplierResponse addSupplier(SupplierCreateRequest request);
    public SupplierResponse updateSupplier(String id, SupplierUpdateRequest request);
    public void deleteSupplier(String  id);
}
