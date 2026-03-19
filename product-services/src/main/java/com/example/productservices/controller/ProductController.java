package com.example.productservices.controller;

import com.example.common.dto.WebResponse;
import com.example.productservices.dto.ProductCreateRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.dto.ProductUpdateRequest;
import com.example.productservices.dto.SearchProductResult;
import com.example.productservices.filter.ProductFilter;
import com.example.productservices.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/karyawarna/admin")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/product")
    ResponseEntity<ProductResponse>addProduct(@Valid @RequestBody ProductCreateRequest request){
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    ResponseEntity<Page<SearchProductResult>> searchProduct(@ModelAttribute ProductFilter productFilter){
        Pageable pageable = PageRequest.of(productFilter.getPage(), productFilter.getSize());
        Page<SearchProductResult> productPage = productService.searchProduct(productFilter, pageable);
        return ResponseEntity.ok(productPage);
    }

    @PatchMapping("/update/product/{id}")
    ResponseEntity<WebResponse<ProductResponse>> updateProduct(@PathVariable String id, @RequestBody ProductUpdateRequest productRequest){
        ProductResponse productResponse = productService.updateProduct(id,productRequest);

        WebResponse<ProductResponse> response = WebResponse.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .code("OK")
                .message("Berhasil mengupdate barang")
                .data(productResponse)
                .build();

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/delete/product/{id}")
    ResponseEntity<WebResponse<String>> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        WebResponse<String> response = WebResponse.<String>builder()
                    .status(HttpStatus.OK.value())
                    .code("OK")
                    .message("Berhasil menghapus barang")
                    .data("deleted Id : " + id)
                    .build();

        return ResponseEntity.ok(response);
    }



}
