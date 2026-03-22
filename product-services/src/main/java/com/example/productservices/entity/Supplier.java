package com.example.productservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @Column(name = "supplier_code", unique = true)
    private String supplierCode;

    @Column(name = "contact_person", nullable = false)
    private String contactName;

    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @Column(name = "description", nullable = true)
    private String desc;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<Brand> brandList = new ArrayList<>();


    public void addProductToList(Product product){
        productList.add(product);
        product.setSupplier(this);
    }

    public void addBrandToList(Brand brand){
        brandList.add(brand);
        brand.setSupplier(this);
    }

    public void deleteBrandFromList(Brand brand){
        brandList.remove(brand);
    }
}

