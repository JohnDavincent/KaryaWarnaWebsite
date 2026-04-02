package com.example.productservices.service;

import com.example.productservices.dto.supplier.SupplierCreateRequest;
import com.example.productservices.dto.supplier.SupplierResponse;
import com.example.productservices.dto.supplier.SupplierUpdateRequest;
import com.example.productservices.projection.SupplierProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService{

    public SupplierResponse addSupplier(SupplierCreateRequest request);
    public SupplierResponse updateSupplier(String id, SupplierUpdateRequest request);
    public void deleteSupplier(String  id);
    public List<SupplierProjection> viewAll();
}
