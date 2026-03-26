package com.example.productservices.controller;

import com.example.common.dto.WebResponse;
import com.example.productservices.dto.brand.BrandCreateRequest;
import com.example.productservices.dto.brand.BrandCreateResponse;
import com.example.productservices.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/karyawarna/admin")
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/create/brand")
    ResponseEntity<WebResponse<BrandCreateResponse>> createBrand (@RequestBody BrandCreateRequest request){
        BrandCreateResponse brand = brandService.createBrand(request);
        WebResponse<BrandCreateResponse> response = WebResponse.<BrandCreateResponse>builder()
                .status(HttpStatus.OK.value())
                .code("OK")
                .message("Success create new Brand")
                .data(brand)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
