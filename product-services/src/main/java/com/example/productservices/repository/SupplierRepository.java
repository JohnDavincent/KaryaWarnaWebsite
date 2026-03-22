package com.example.productservices.repository;

import com.example.productservices.entity.Product;
import com.example.productservices.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    Optional<Supplier> findBySupplierName(String name);
    boolean existsBySupplierName(String name);
    boolean existsBySupplierCode(String supplierCode);
    Optional<Supplier> findBySupplierCode(String supplierCode);
    void deleteBySupplierCode(String supplierCode);

}
