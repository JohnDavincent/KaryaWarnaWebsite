package com.example.productservices.service;

import com.example.productservices.dto.ProductRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.dto.SearchProductRequest;
import com.example.productservices.dto.SearchProductResult;
import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {
    public ProductResponse createProduct(ProductRequest request);
    public Page<SearchProductResult> searchProduct(ProductFilter productFilter, Pageable pageable);
    public ProductResponse updateProduct(Long productId, ProductRequest request);

}
