package com.example.productservices.service;

import com.example.productservices.dto.product.*;
import com.example.productservices.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public interface ProductService {
    public ProductResponse createProduct(ProductCreateRequest request);
    public Page<SearchProductResult> searchProduct(ProductFilter productFilter, Pageable pageable);
    public ProductResponse updateProduct(String productCode, ProductUpdateRequest request);
    public void deleteProduct(String productId);
    public Page<ProductResponse> viewProduct(Pageable pageable);
    public OrderDetailProduct getProductDetail(UUID id);
    public void updateStock(Map<UUID,Integer> orderPackage);

}
