package com.example.productservices.service;

import com.example.common.exception.CategoryNotExistException;
import com.example.common.exception.ProductExistException;
import com.example.common.exception.ProductNotExistException;
import com.example.common.exception.SupplierNotExistException;
import com.example.productservices.dto.ProductRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.dto.SearchProductRequest;
import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.entity.Supplier;
import com.example.productservices.mapper.ProductMapper;
import com.example.productservices.repository.ProductCategoryRepository;
import com.example.productservices.repository.ProductRepository;
import com.example.productservices.repository.SupplierRepository;
import com.example.productservices.spesification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsByProductName(request.getProductName())) {
            throw new ProductExistException("Product sudah ada di dalam database");
        }
        ProductCategory category = productCategoryRepository.findByCategory(request.getCategory()).orElseThrow(() -> new CategoryNotExistException("category not found"));
        Supplier supplier = supplierRepository.findByName(request.getSupplier()).orElseThrow(() -> new SupplierNotExistException("Supplier doesn't exist"));


        String productId = generateProductId(category);
        Product createProduct = productMapper.mapToProduct(request);

        createProduct.setId(productId);
        createProduct.setProductCategory(category);
        createProduct.setSupplier(supplier);
        productRepository.save(createProduct);

        category.addProductToList(createProduct);
        supplier.addProductToList(createProduct);
        productCategoryRepository.save(category);
        supplierRepository.save(supplier);

        return productMapper.mapToProductResponse(createProduct);
    }

    private String generateProductId(ProductCategory category) {
        int next = category.getCurrentSeq() + 1;
        category.setCurrentSeq(next);
        productCategoryRepository.save(category);
        return category.getPrefix() + "-" + String.format("%03d", next);
    };

    @Override
    public Page<Product> searchProduct(String name, Long category, Long supplier, int maxStock, int minStock, Pageable pageable) {
        Specification<Product> specification = Specification
                .where(ProductSpecification.hasNameLike(name))
                .and(ProductSpecification.filterByCategory(category))
                .and(ProductSpecification.filterBySupplier(supplier))
                .and(ProductSpecification.filterByStock(minStock,maxStock));

        return productRepository.findAll(specification, pageable);
    }












}
