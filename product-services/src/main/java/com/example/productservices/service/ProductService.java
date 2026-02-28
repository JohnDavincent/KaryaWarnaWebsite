package com.example.productservices.service;

import com.example.productservices.dto.ProductRequest;
import com.example.productservices.dto.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public ProductResponse createProduct(ProductRequest request);
}
