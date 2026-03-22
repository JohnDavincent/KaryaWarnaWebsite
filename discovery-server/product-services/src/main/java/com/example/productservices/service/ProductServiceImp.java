package com.example.productservices.service;

import com.example.common.exception.CategoryNotExistException;
import com.example.common.exception.ProductExistException;
import com.example.common.exception.ProductNotExistException;
import com.example.common.exception.SupplierNotExistException;
import com.example.productservices.dto.ProductCreateRequest;
import com.example.productservices.dto.ProductResponse;
import com.example.productservices.dto.ProductUpdateRequest;
import com.example.productservices.dto.SearchProductResult;
import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.entity.Supplier;
import com.example.productservices.filter.ProductFilter;
import com.example.productservices.mapper.ProductMapper;
import com.example.productservices.repository.ProductCategoryRepository;
import com.example.productservices.repository.ProductRepository;
import com.example.productservices.repository.SupplierRepository;
import com.example.productservices.spesification.ProductSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
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
    public Page<SearchProductResult> searchProduct(ProductFilter productFilter, Pageable pageable) {
        Specification<Product> specification = Specification
                .where(ProductSpecification.hasNameLike(productFilter.getProductName()))
                .and(ProductSpecification.filterByCategory(productFilter.getCategoryId()))
                .and(ProductSpecification.filterBySupplier(productFilter.getSupplierId())
                .and(ProductSpecification.filterByStock(productFilter.getMinStock(), productFilter.getMaxStock())));

        Page<Product> productData = productRepository.findAll(specification,pageable);
        return productData.map(product ->{
                SearchProductResult resultProduct =  SearchProductResult.builder()
                        .productName(product.getProductName())
                        .stock(product.getStock())
                        .description(product.getDescription())
                        .sellPrice(product.getSellPrice())
                        .purchasedPrice(product.getPurchasePrice())
                        .lastUpdate(product.getUpdateAt())
                        .build();

                if(product.getProductCategory() != null){
                    resultProduct.setCategoryName(product.getProductCategory().getCategory());
                }

                if(product.getSupplier() != null){
                    resultProduct.setSupplierName(product.getSupplier().getName());
                }

                return resultProduct;

        });
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(String productId, ProductUpdateRequest request) {
        if(request == null){
            throw new RuntimeException("There is no input change");
        }
        Product existProduct = productRepository.findById(productId).orElseThrow(() -> new ProductNotExistException("Product Not exist"));
        if(request.getProductName() != null){
            existProduct.setProductName(request.getProductName());
        }

        if(request.getDescription() != null){
            existProduct.setDescription(request.getDescription());
        }
        if(request.getPurchasePrice() != null){
            existProduct.setPurchasePrice(request.getPurchasePrice());
        }
        if(request.getSellPrice() != null){
            existProduct.setSellPrice(request.getSellPrice());
        }
        if(request.getCategoryId() != null && productCategoryRepository.existsById(request.getCategoryId())){
            ProductCategory existCategory = productCategoryRepository.getReferenceById(request.getCategoryId());
            existProduct.setProductCategory(existCategory);
        }

        if(request.getSupplierId() != null && supplierRepository.existsById(request.getSupplierId())){
            Supplier existSupplier = supplierRepository.getReferenceById(request.getSupplierId());
            existProduct.setSupplier(existSupplier);
        }

        productRepository.save(existProduct);
        return productMapper.mapToProductResponse(existProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        if(!productRepository.existsById(productId)){
            throw new ProductNotExistException("Product with id : " + productId + "not exist");
        }

        productRepository.deleteById(productId);
    }


}
