package com.example.productservices.service;

import com.example.productservices.dto.brand.BrandCreateRequest;
import com.example.productservices.dto.brand.BrandCreateResponse;
import com.example.productservices.dto.brand.BrandUpdateRequest;
import com.example.productservices.dto.brand.BrandUpdateResponse;
import org.springframework.stereotype.Service;

@Service
public interface BrandService {
    public BrandCreateResponse createBrand (BrandCreateRequest request);
    public BrandUpdateResponse editBrand (String brandCode, BrandUpdateRequest request);
}
