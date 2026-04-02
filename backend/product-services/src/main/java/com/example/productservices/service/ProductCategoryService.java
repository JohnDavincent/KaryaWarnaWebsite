package com.example.productservices.service;

import com.example.productservices.dto.category.CategoryRequest;
import com.example.productservices.dto.category.CategoryResponse;
import com.example.productservices.projection.CategoryProjection;

import java.util.List;

public interface ProductCategoryService {
    public CategoryResponse createCategory(CategoryRequest request);
    public CategoryResponse updateCategory(String categoryCode, CategoryRequest request);
    public List<CategoryProjection> viewAll();
}
