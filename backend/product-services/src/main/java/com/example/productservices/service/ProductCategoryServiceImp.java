package com.example.productservices.service;

import com.example.common.exception.CategoryNotExistException;
import com.example.productservices.dto.category.CategoryRequest;
import com.example.productservices.dto.category.CategoryResponse;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.mapper.ProductMapper;
import com.example.productservices.projection.CategoryProjection;
import com.example.productservices.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCategoryServiceImp implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductMapper productMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        if(productCategoryRepository.existsByCategoryName(request.getCategoryName())){
            throw new DataIntegrityViolationException("Category already exists");
        }
        if(productCategoryRepository.existsByCategoryName(request.getCategoryCode())){
            throw new DataIntegrityViolationException("Prefix already exists");
        }

        ProductCategory createCategory = ProductCategory.builder()
                .categoryName(request.getCategoryName())
                .categoryCode(request.getCategoryCode())
                .productList(new ArrayList<>())
                .currentSeq(0)
                .build();


        productCategoryRepository.save(createCategory);

        return CategoryResponse.builder()
                .categoryName(createCategory.getCategoryName())
                .categoryCode(createCategory.getCategoryCode())
                .build();
    }


    public CategoryResponse updateCategory(String categoryCode, CategoryRequest request){

        ProductCategory category = productCategoryRepository.findByCategoryCode(categoryCode).orElseThrow(() -> new CategoryNotExistException("Category with code " + request.getCategoryCode() + " doesn't exist"));
        if(request == null){
            throw new RuntimeException("Please fill the at least one field");
        }
        if(request.getCategoryCode() != null){
            category.setCategoryCode(request.getCategoryCode());
        }

        if(request.getCategoryName() != null){
            category.setCategoryName(request.getCategoryName());
        }

        productCategoryRepository.save(category);
        return productMapper.mapToCategoryResponse(category);

    }

    @Override
    public List<CategoryProjection> viewAll() {
        return productCategoryRepository.viewAll();
    }

}
