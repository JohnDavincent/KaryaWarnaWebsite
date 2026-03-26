package com.example.productservices.entity;

import com.example.common.entity.BaseEntity;
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
@Table(name = "brand")
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "brand_name", unique = true)
    private String brandName;

    @Column(name = "brand_code", unique = true)
    private String brandCode;

   @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @Builder.Default
    private List<Product> productList = new ArrayList<>();

    public void addProductToList(Product product){
        this.productList.add(product);
        product.setBrand(this);
    }




}
