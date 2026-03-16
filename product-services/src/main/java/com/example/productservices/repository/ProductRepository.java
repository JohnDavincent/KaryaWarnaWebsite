package com.example.productservices.repository;

import com.example.productservices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    boolean existsByProductName(String productName);
    Optional<Product> findByProductName(String productName);
    Optional<Product> findById(String id);

    @Query("SELECT p FROM Product p WHERE p.productCategory = :categoryName ")
    List<Product> findAllByCategory(@Param("categoryName") String categoryName);
}
