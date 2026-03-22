package com.example.productservices.controller;

import com.example.common.dto.WebResponse;
import com.example.productservices.dto.category.CategoryRequest;
import com.example.productservices.dto.category.CategoryResponse;
import com.example.productservices.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/karyawarna/admin")
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

    @PatchMapping("/update/category/{categoryCode}")
    public ResponseEntity<WebResponse<CategoryResponse>> updateCategory(@PathVariable String categoryCode, @RequestBody CategoryRequest categoryRequest){
        CategoryResponse data = productCategoryService.updateCategory(categoryCode,categoryRequest);
        WebResponse<CategoryResponse> response = WebResponse.<CategoryResponse>builder()
                .status(HttpStatus.OK.value())
                .code("UPDATED")
                .message("Success update the category!")
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }


}
