package com.example.productservices.service;

import com.example.productservices.dto.ProductRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.dto.SearchProductRequest;
import com.example.productservices.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {
    public ProductResponse createProduct(ProductRequest request);
    public ProductResponse searchProduct(SearchProductRequest searchReq);

}
