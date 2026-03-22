package com.example.productservices.service;

import com.example.productservices.dto.CategoryRequest;
import com.example.productservices.dto.CategoryResponse;

public interface ProductCategoryService {
    public CategoryResponse createCategory(CategoryRequest request);
}
