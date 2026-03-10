package com.example.productservices.mapper;

import com.example.productservices.dto.ProductRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapper {
    public Product mapToProduct(ProductRequest request){
        return Product.builder()
                .productName(request.getProductName())
                .stock(request.getStock())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .purchasePrice(request.getPurchasePrice())
                .build();
    }

    public ProductResponse mapToProductResponse(Product product, ProductCategory productCategory){
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .stock(product.getStock())
                .sellPrice(product.getSellPrice())
                .purchasePrice(product.getPurchasePrice())
                .description(product.getDescription())
                .category(productCategory.getCategory())
                .build();
    }

}
