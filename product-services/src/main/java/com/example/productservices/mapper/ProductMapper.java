package com.example.productservices.mapper;

import com.example.productservices.dto.brand.BrandCreateRequest;
import com.example.productservices.dto.brand.BrandCreateResponse;
import com.example.productservices.dto.brand.BrandUpdateRequest;
import com.example.productservices.dto.brand.BrandUpdateResponse;
import com.example.productservices.dto.category.CategoryResponse;
import com.example.productservices.dto.product.ProductCreateRequest;
import com.example.productservices.dto.product.ProductResponse;
import com.example.productservices.dto.product.SearchProductResult;
import com.example.productservices.dto.supplier.SupplierCreateRequest;
import com.example.productservices.dto.supplier.SupplierResponse;
import com.example.productservices.entity.Brand;
import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.entity.Supplier;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ProductMapper {
    public Product mapToProduct(ProductCreateRequest request) {
        return Product.builder()
                .productName(request.getProductName())
                .stock(request.getStock())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .purchasePrice(request.getPurchasePrice())
                .build();
    }

    public ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .stock(product.getStock())
                .sellPrice(product.getSellPrice())
                .purchasePrice(product.getPurchasePrice())
                .description(product.getDescription())
                .category(product.getProductCategory() != null ? product.getProductCategory().getCategoryName() : null)
                .supplier(product.getSupplier() != null ? product.getSupplier().getSupplierName() : null)
                .build();
    }

    public SearchProductResult mapToSearchResult(Product product) {
        return SearchProductResult.builder()
                .productName(product.getProductName())
                .stock(product.getStock())
                .description(product.getDescription())
                .sellPrice(product.getSellPrice())
                .purchasedPrice(product.getPurchasePrice())
                .lastUpdate(product.getUpdateAt())
                .categoryName(product.getProductCategory() != null ? product.getProductCategory().getCategoryName() : null)
                .supplierName(product.getSupplier() != null ? product.getSupplier().getSupplierName() : null)
                .build();
    }

    public Supplier mapToSupplier(SupplierCreateRequest supplierRequest) {
        return Supplier.builder()
                .supplierName(supplierRequest.getSupplierName())
                .contactName(supplierRequest.getContactPerson())
                .desc(supplierRequest.getDesc())
                .phoneNumber(supplierRequest.getPhoneNumber())
                .productList(new ArrayList<>())
                .build();
    }

    public SupplierResponse mapToSupplierResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .supplierName(supplier.getSupplierName())
                .phoneNumber(supplier.getPhoneNumber())
                .contactName(supplier.getContactName())
                .desc(supplier.getDesc())
                .build();
    }

    public Brand mapToBrand(BrandCreateRequest request) {
        return Brand.builder()
                .brandName(request.getBrandName())
                .brandCode(request.getBrandCode())
                .build();
    }

    public BrandCreateResponse mapToBrandResponse(Brand brand) {
        List<ProductCategory> categories = brand.getCategoryList();
        return BrandCreateResponse.builder()
                .brandName(brand.getBrandName())
                .brandCode(brand.getBrandCode())
                .createdAt(brand.getCreatedAt())
                .createdBy(brand.getCreatedBy())
                .supplierName(brand.getSupplier() != null ? brand.getSupplier().getSupplierName() : null)
                .categoryIds(categories != null ? categories.stream().map(ProductCategory::getId).collect(Collectors.toList()) : null)
                .build();
    }

    public BrandUpdateResponse mapToBrandUpdateResponse(Brand brand){
        List<ProductCategory> categories = brand.getCategoryList();
        return BrandUpdateResponse.builder()
                .UpdateAt(brand.getUpdateAt())
                .UpdateBy(brand.getLastModifiedBy())
                .brandName(brand.getBrandCode())
                .supplierName(brand.getSupplier().getSupplierName())
                .categoryIds(categories != null ? categories.stream().map(ProductCategory::getId).collect(Collectors.toList()) : null)
                .build();
    }

    public CategoryResponse mapToCategoryResponse(ProductCategory productCategory){
        return CategoryResponse.builder()
                .categoryCode(productCategory.getCategoryCode())
                .categoryName(productCategory.getCategoryName())
                .build();
    }


}
