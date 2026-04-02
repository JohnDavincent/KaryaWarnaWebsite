package com.example.productservices.service;

import com.example.productservices.dto.brand.*;
import com.example.productservices.projection.BrandProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    public BrandCreateResponse createBrand (BrandCreateRequest request);
    public BrandUpdateResponse editBrand (String brandCode, BrandUpdateRequest request);
    public List<BrandProjection> viewALl();
}
