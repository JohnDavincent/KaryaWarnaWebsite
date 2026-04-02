package com.example.productservices.service;

import com.example.common.exception.*;
import com.example.productservices.dto.brand.*;
import com.example.productservices.entity.Brand;
import com.example.productservices.mapper.ProductMapper;
import com.example.productservices.repository.BrandRepository;
import com.example.productservices.projection.BrandProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

    @Override
    public BrandCreateResponse createBrand(BrandCreateRequest request) {
        if(request == null){
           throw new  RuntimeException("There is no input");
        }
        if(brandRepository.existsByBrandName(request.getBrandName())){
            throw new BrandAlreadyExistException("Brand with name : " + request.getBrandName() + " Already exist!!");
        }

        if(brandRepository.existsByBrandCode(request.getBrandCode())){
            throw new PrefixAlreadyExistException(("Prefix with name : " + request.getBrandCode() + " Is already exist"));
        }
        Brand createBrand = productMapper.mapToBrand(request);

        brandRepository.save(createBrand);
        return productMapper.mapToBrandResponse(createBrand);
    }

    @Override
    public BrandUpdateResponse editBrand(String brandCode, BrandUpdateRequest request) {
        Brand brand = brandRepository.findByBrandCode(brandCode).orElseThrow(() -> new BrandNotExistException("brand with brand code " + brandCode + " Doesn't exist"));
        if(request == null){
            throw new RuntimeException("Must input at least one field");
        }

        if(request.getBrandCode() != null){
            brand.setBrandCode(request.getBrandCode());
        }
        if(request.getBrandName() != null){
            brand.setBrandName(request.getBrandName());
        }

        return productMapper.mapToBrandUpdateResponse(brand);
    }

    @Override
    public List<BrandProjection> viewALl() {
        return brandRepository.viewAll();
    }

}
