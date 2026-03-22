package com.example.productservices.repository;

import com.example.productservices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    boolean existsByProductName(String productName);
    Optional<Product> findByProductName(String productName);
    Optional<Product> findByProductCode(String productCode);
    boolean existsByProductCode(String productCode);
    void deleteByProductCode(String productCode);
}
