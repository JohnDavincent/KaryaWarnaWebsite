package com.example.productservices.repository;

import com.example.productservices.entity.Product;
import com.example.productservices.entity.Supplier;
import com.example.productservices.projection.BrandProjection;
import com.example.productservices.projection.SupplierProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    Optional<Supplier> findBySupplierName(String name);
    boolean existsBySupplierName(String name);
    boolean existsBySupplierCode(String supplierCode);
    Optional<Supplier> findBySupplierCode(String supplierCode);
    void deleteBySupplierCode(String supplierCode);

    @Query(
            value = """
                SELECT sp.supplier_name AS supplierName, sp.supplier_code AS supplierCode
                FROM inventory_services.supplier sp
            """,nativeQuery = true
    )
    List<SupplierProjection> viewAll();

}
