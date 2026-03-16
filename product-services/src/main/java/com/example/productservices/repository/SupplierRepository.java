package com.example.productservices.repository;

import com.example.productservices.entity.Product;
import com.example.productservices.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier,String> {
    Optional<?> findByName(String name);
    boolean existsByName(String name);

}
