package com.example.productservices.repository;

import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    @Query("SELECT c FROM ProductCategory c WHERE c.categoryName = :categoryName")
    Optional<ProductCategory> findByCategoryName(@Param("categoryName")String categoryName);

    boolean existsByCategoryName(String categoryName);
    boolean existsByCategoryCode(String categoryCode);
    Optional<ProductCategory> findByCategoryCode(String categoryCode);
    List<ProductCategory> findByBrandList_Id(UUID brandId);

}
