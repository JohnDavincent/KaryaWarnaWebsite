package com.example.productservices.service;

import com.example.productservices.dto.CategoryRequest;
import com.example.productservices.dto.CategoryResponse;
import com.example.productservices.entity.ProductCategory;
import com.example.common.exception.ProductExistException;
import com.example.productservices.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCategoryServiceImp implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        if(productCategoryRepository.existsByCategory(request.getCategoryName())){
            throw new DataIntegrityViolationException("Category already exists");
        }
        if(productCategoryRepository.existsByPrefix(request.getPrefix())){
            throw new DataIntegrityViolationException("Prefix already exists");
        }

        ProductCategory createCategory = ProductCategory.builder()
                .category(request.getCategoryName())
                .prefix(request.getPrefix())
                .productList(new ArrayList<>())
                .currentSeq(0)
                .build();

        productCategoryRepository.save(createCategory);

        return CategoryResponse.builder()
                .categoryName(createCategory.getCategory())
                .prefix(createCategory.getPrefix())
                .build();
    }
}
