package com.example.productservices.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productCategory")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "prefix")
    private String prefix;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();

    @Column(name = "sequence")
    private Integer currentSeq;
}
