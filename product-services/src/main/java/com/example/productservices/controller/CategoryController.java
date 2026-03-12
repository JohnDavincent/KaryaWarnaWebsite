package com.example.productservices.controller;

import com.example.common.dto.WebResponse;
import com.example.productservices.dto.CategoryRequest;
import com.example.productservices.dto.CategoryResponse;
import com.example.productservices.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/karyawarna")
@RequiredArgsConstructor
public class CategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping("/create/category")
    public ResponseEntity<WebResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request){
        CategoryResponse categoryResponse = productCategoryService.createCategory(request);
        WebResponse<CategoryResponse> webResponse =  new WebResponse<>(
                HttpStatus.CREATED.value(),
                "Berhasil menambahkan category",
                "OK",
                categoryResponse
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);
    }
}
