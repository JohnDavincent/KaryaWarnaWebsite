package com.example.productservices.repository;

import com.example.productservices.entity.Product;
import com.example.productservices.entity.ProductCategory;
import com.example.productservices.projection.CategoryProjection;
import com.example.productservices.projection.SupplierProjection;
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

    @Query(
            value = """
                SELECT cp.category_name, cp.category_code 
                FROM inventory_services.category cp
            """,nativeQuery = true
    )
    List<CategoryProjection> viewAll();
}
