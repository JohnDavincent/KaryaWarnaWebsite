package com.example.productservices.service;

import com.example.productservices.dto.ProductCreateRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.dto.ProductUpdateRequest;
import com.example.productservices.dto.SearchProductResult;
import com.example.productservices.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public ProductResponse createProduct(ProductCreateRequest request);
    public Page<SearchProductResult> searchProduct(ProductFilter productFilter, Pageable pageable);
    public ProductResponse updateProduct(String productId, ProductUpdateRequest request);
    public void deleteProduct(String productId);

}
