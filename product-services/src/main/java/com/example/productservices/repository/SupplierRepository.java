package com.example.productservices.repository;

import com.example.productservices.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,String> {
}
