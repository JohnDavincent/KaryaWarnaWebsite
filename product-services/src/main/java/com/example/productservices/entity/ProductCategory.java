package com.example.productservices.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "category_code", unique = true)
    private String categoryCode;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Product> productList = new ArrayList<>();

    @Column(name = "sequence")
    @Version
    private Integer currentSeq;

    public void addProductToList(Product product){
        this.productList.add(product);
        product.setProductCategory(this);
    }


}
