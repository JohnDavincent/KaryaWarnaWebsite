package com.example.productservices.repository;

import com.example.productservices.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("SELECT c FROM ProductCategory c WHERE c.category = :categoryName")
    Optional<ProductCategory> findByCategory(@Param("categoryName")String categoryName);

    boolean existsByCategory(String category);
    boolean existsByPrefix(String prefix);


}
