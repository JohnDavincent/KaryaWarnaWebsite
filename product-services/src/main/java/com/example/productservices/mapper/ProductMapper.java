package com.example.productservices.mapper;

import com.example.productservices.dto.ProductRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.dto.SupplierRequest;
import com.example.productservices.dto.SupplierResponse;
import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.entity.Supplier;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

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

    public ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .stock(product.getStock())
                .sellPrice(product.getSellPrice())
                .purchasePrice(product.getPurchasePrice())
                .description(product.getDescription())
                .category(product.getProductCategory().getCategory())
                .build();
    }

    public Supplier mapToSupplier(SupplierRequest supplierRequest){
        return Supplier.builder()
                .name(supplierRequest.getSupplierName())
                .contactName(supplierRequest.getContactPerson())
                .desc(supplierRequest.getDesc())
                .phoneNumber(supplierRequest.getPhoneNumber())
                .productList(new ArrayList<>())
                .build();
    }

    public SupplierResponse mapToSupplierResponse(Supplier supplier){
        return SupplierResponse.builder()
                .Supplier(supplier.getContactName())
                .phoneNumber(supplier.getPhoneNumber())
                .contactName(supplier.getContactName())
                .desc(supplier.getDesc())
                .build();
    }




}
