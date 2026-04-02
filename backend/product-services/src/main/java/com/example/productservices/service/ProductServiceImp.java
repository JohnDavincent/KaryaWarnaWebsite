package com.example.productservices.service;

import com.example.common.exception.*;
import com.example.productservices.dto.product.*;
import com.example.productservices.entity.Brand;
import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.entity.Supplier;
import com.example.productservices.filter.ProductFilter;
import com.example.productservices.mapper.ProductMapper;
import com.example.productservices.repository.BrandRepository;
import com.example.productservices.repository.ProductCategoryRepository;
import com.example.productservices.repository.ProductRepository;
import com.example.productservices.repository.SupplierRepository;
import com.example.productservices.spesification.ProductSpecification;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        if (productRepository.existsByProductName(request.getProductName())) {
            throw new ProductExistException("Product already exist in database");
        }

        if (request.getBrand() == null && request.getSupplier() == null) {
            throw new SupplierNotExistException("Please input at least one, supplier or brand");
        }

        ProductCategory category = productCategoryRepository.findByCategoryName(request.getCategory())
                .orElseThrow(() -> new CategoryNotExistException("Category with name : " + request.getCategory() + " doesn't exist"));
        
        String productCode = generateProductId(category);
        Product createProduct = productMapper.mapToProduct(request);

        createProduct.setProductCode(productCode);

        if (request.getBrand() != null) {
            Brand brand = brandRepository.findByBrandName(request.getBrand())
                    .orElseThrow(() -> new BrandNotExistException("Brand with name : " + request.getBrand() + " doesn't exist"));
            createProduct.setBrand(brand);

        }

        Supplier supplier = supplierRepository.findBySupplierName(request.getSupplier())
                .orElseThrow(() -> new SupplierNotExistException("Supplier with name : " + request.getSupplier() + " doesn't exist"));
        
        createProduct.setProductCategory(category);
        createProduct.setSupplier(supplier);
        productRepository.save(createProduct);

        return productMapper.mapToProductResponse(createProduct);
    }

    private String generateProductId(ProductCategory category) {
        try {
            int next = (category.getCurrentSeq() == null ? 0 : category.getCurrentSeq()) + 1;
            category.setCurrentSeq(next);
            productCategoryRepository.save(category);
            return category.getCategoryCode() + "-" + String.format("%03d", next);
        } catch (ObjectOptimisticLockingFailureException | OptimisticLockException e) {
            log.warn("Concurrent sequence update detected, retrying...");
            ProductCategory refreshed = productCategoryRepository.findById(category.getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            return generateProductId(refreshed);
        }
    }

    @Override
    public Page<SearchProductResult> searchProduct(ProductFilter productFilter, Pageable pageable) {
        Specification<Product> specification = Specification
                .where(ProductSpecification.hasNameLike(productFilter.getProductName()))
                .and(ProductSpecification.filterByCategory(productFilter.getCategoryId()))
                .and(ProductSpecification.filterBySupplier(productFilter.getSupplierId()))
                .and(ProductSpecification.filterByBrand(productFilter.getBrandId()))
                .and(ProductSpecification.filterByStock(productFilter.getMinStock(), productFilter.getMaxStock()))
                .and(ProductSpecification.fetchRelations());

        Page<Product> productData = productRepository.findAll(specification, pageable);
        return productData.map(productMapper::mapToSearchResult);
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(String productCode, ProductUpdateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("There is no input change");
        }
        
        Product existProduct = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotExistException("Product Not exist"));
        
        if (request.getProductName() != null) {
            existProduct.setProductName(request.getProductName());
        }

        if (request.getDescription() != null) {
            existProduct.setDescription(request.getDescription());
        }
        if (request.getPurchasePrice() != null) {
            existProduct.setPurchasePrice(request.getPurchasePrice());
        }
        if (request.getSellPrice() != null) {
            existProduct.setSellPrice(request.getSellPrice());
        }
        if (request.getCategoryId() != null && productCategoryRepository.existsById(request.getCategoryId())) {
            ProductCategory existCategory = productCategoryRepository.getReferenceById(request.getCategoryId());
            existProduct.getProductCategory().getProductList().remove(existProduct);
            existCategory.addProductToList(existProduct);

        }

        if (request.getSupplierId() != null && supplierRepository.existsById(request.getSupplierId())) {
            Supplier existSupplier = supplierRepository.getReferenceById(request.getSupplierId());
            existProduct.getSupplier().getProductList().remove(existProduct);
            existSupplier.addProductToList(existProduct);

        }

        if(request.getBrandId() != null && brandRepository.existsById(request.getBrandId())){
            Brand existBrand = brandRepository.getReferenceById(request.getBrandId());
            existProduct.getBrand().getProductList().remove(existProduct);
            existBrand.addProductToList(existProduct);
        }

        productRepository.save(existProduct);
        return productMapper.mapToProductResponse(existProduct);
    }

    @Override
    public void deleteProduct(String productCode) {
        if (!productRepository.existsByProductCode(productCode)) {
            throw new ProductNotExistException("Product with id : " + productCode + " not exist");
        }

        productRepository.deleteByProductCode(productCode);
    }

    @Override
    public Page<ProductResponse> viewProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> ProductResponse.builder()
                        .productName(product.getProductName())
                        .stock(product.getStock())
                        .description(product.getDescription())
                        .category(product.getProductCategory().getCategoryName())
                        .sellPrice(product.getSellPrice())
                        .purchasePrice(product.getPurchasePrice())
                        .createAt(product.getCreatedAt())
                        .supplier(product.getSupplier().getSupplierName())
                        .productCode(product.getProductCode())
                        .brand(product.getBrand().getBrandName())
                        .build()
                );

    }

    @Override
    public OrderDetailProduct getProductDetail(UUID id) {
        Product existProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotExistException("Product with id : " + id + " not exist"));
        return OrderDetailProduct.builder()
                .productId(existProduct.getId())
                .productName(existProduct.getProductName())
                .pricePerUnit(existProduct.getSellPrice())
                .stock(existProduct.getStock())
                .build();
    }

    @Transactional
    @Override
    public void updateStock(Map<UUID, Integer> orderPackage) {

        for(Map.Entry<UUID,Integer> entry : orderPackage.entrySet()){
            Product product = productRepository.findById(entry.getKey()).orElseThrow(() -> new ProductNotExistException("Product with product id : " + entry.getKey() + " not exist!"));
            if(product.getStock() < entry.getValue()){
                throw new RuntimeException("Product : " + product.getProductName() + " stock is not enough");
            }
            product.setStock(product.getStock() - entry.getValue());
            productRepository.save(product);
        }
    }
}
