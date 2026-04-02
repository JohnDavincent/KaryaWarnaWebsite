package com.example.productservices.repository;

import com.example.productservices.entity.Brand;
import com.example.productservices.projection.BrandProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
    Optional<Brand> findByBrandName(String name);
    boolean existsByBrandName(String name);
    boolean existsByBrandCode(String brandCode);
    Optional<Brand> findByBrandCode(String brandCode);

    @Query(
            value = """
                    SELECT br.brand_name as brandName, br.brand_code as brandCode
                    FROM inventory_services.brand br
            """, nativeQuery = true
    )
    List<BrandProjection> viewAll();

}
