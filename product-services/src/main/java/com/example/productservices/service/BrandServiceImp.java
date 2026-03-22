package com.example.productservices.service;

import com.example.common.exception.*;
import com.example.productservices.dto.brand.BrandCreateRequest;
import com.example.productservices.dto.brand.BrandCreateResponse;
import com.example.productservices.dto.brand.BrandUpdateRequest;
import com.example.productservices.dto.brand.BrandUpdateResponse;
import com.example.productservices.entity.Brand;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.entity.Supplier;
import com.example.productservices.mapper.ProductMapper;
import com.example.productservices.repository.BrandRepository;
import com.example.productservices.repository.ProductCategoryRepository;
import com.example.productservices.repository.SupplierRepository;
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
    private final SupplierRepository supplierRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private Supplier supplier;

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

        Supplier supplier = supplierRepository.findBySupplierName(request.getSupplierName()).orElseThrow(() -> new SupplierNotExistException("Supplier with id " + request.getSupplierName() +  " Not exist, please create the supplier first"));
        Brand createBrand = productMapper.mapToBrand(request);
        createBrand.setSupplier(supplier);
        supplier.addBrandToList(createBrand);

        // Link categories to brand
        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            List<ProductCategory> categories = productCategoryRepository.findAllById(request.getCategoryIds());
            if (categories.size() != request.getCategoryIds().size()) {
                throw new CategoryNotExistException("Some categories not found");
            }
            for (ProductCategory category : categories) {
                createBrand.getCategoryList().add(category);
                category.getBrandList().add(createBrand);
            }
        }

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
        if(request.getSupplier() != null){
            Supplier updateSupplier = supplierRepository.findById(request.getSupplier()).orElseThrow(() -> new SupplierNotExistException("Supplier with id : " + request.getSupplier() + " doesn't exist"));
            supplier.deleteBrandFromList(brand);
            brand.setSupplier(updateSupplier);
            updateSupplier.addBrandToList(brand);
        }
        //CARI UNTUK KASUS MENGUBAH ID DARI CATEGORYNYA

        return productMapper.mapToBrandUpdateResponse(brand);
    }



}
