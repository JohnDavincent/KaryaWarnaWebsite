package com.example.productservices.repository;

import com.example.productservices.dto.brand.BrandCreateRequest;
import com.example.productservices.dto.brand.BrandCreateResponse;
import com.example.productservices.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
    Optional<Brand> findByBrandName(String name);
    boolean existsByBrandName(String name);
    boolean existsByBrandCode(String brandCode);
    Optional<Brand> findByBrandCode(String brandCode);
    List<Brand> findByCategoryList_Id(UUID categoryId);

}
