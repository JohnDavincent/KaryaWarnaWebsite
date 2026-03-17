package com.example.productservices.controller;

import com.example.productservices.dto.ProductRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.dto.SearchProductResult;
import com.example.productservices.entity.Product;
import com.example.productservices.filter.ProductFilter;
import com.example.productservices.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/product")
    ResponseEntity<ProductResponse>addProduct(@RequestBody ProductRequest request){
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    ResponseEntity<Page<SearchProductResult>> searchProduct(@ModelAttribute ProductFilter productFilter){
        Pageable pageable = PageRequest.of(productFilter.getPage(), productFilter.getSize());
        Page<SearchProductResult> productPage = productService.searchProduct(productFilter, pageable);
        return ResponseEntity.ok(productPage);
    }


}
