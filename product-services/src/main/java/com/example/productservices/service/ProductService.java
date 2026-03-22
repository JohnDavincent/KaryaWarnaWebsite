package com.example.productservices.service;

import com.example.productservices.dto.product.ProductCreateRequest;
import com.example.productservices.dto.product.ProductResponse;
import com.example.productservices.dto.product.ProductUpdateRequest;
import com.example.productservices.dto.product.SearchProductResult;
import com.example.productservices.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public ProductResponse createProduct(ProductCreateRequest request);
    public Page<SearchProductResult> searchProduct(ProductFilter productFilter, Pageable pageable);
    public ProductResponse updateProduct(String productCode, ProductUpdateRequest request);
    public void deleteProduct(String productId);

}
