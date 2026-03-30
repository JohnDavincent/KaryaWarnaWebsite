package com.example.productservices.service;

import com.example.productservices.dto.category.CategoryRequest;
import com.example.productservices.dto.category.CategoryResponse;

public interface ProductCategoryService {
    public CategoryResponse createCategory(CategoryRequest request);
    public CategoryResponse updateCategory(String categoryCode, CategoryRequest request);
}
