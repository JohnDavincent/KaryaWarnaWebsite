package com.example.productservices.repository;

import com.example.productservices.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByCategory(String category);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM product_category pc WHERE pc.category = :category AND pc.prefix = :prefix)", nativeQuery = true)
    boolean existsByCategoryAndPrefixInCategory(@Param("category") String category, @Param("prefix") String prefix);

}
