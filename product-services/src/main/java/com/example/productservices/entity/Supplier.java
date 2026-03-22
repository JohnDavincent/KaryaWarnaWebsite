package com.example.productservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contactPerson", nullable = false)
    private String contactName;

    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @Column(name = "Description", nullable = true)
    private String desc;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();

    public void addProductToList(Product product){
        productList.add(product);
        product.setSupplier(this);
    }
}

