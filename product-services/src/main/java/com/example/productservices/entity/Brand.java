package com.example.productservices.entity;

import com.example.common.entity.BaseEntity;
import com.example.productservices.mapper.ProductMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(mappedBy = "brandList")
    @Builder.Default
    @JsonIgnore
    private List<Product> productList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany
    @JoinTable(
            name = "brand_category",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Builder.Default
    private List<ProductCategory> categoryList = new ArrayList<>();

    //add method
    public void addToProductList(Product product){
        this.productList.add(product);
        product.addBrandToList(this);
    }

    //remove method
    public void removeProductFromList(Product product){
        this.productList.remove(product);
        product.getBrandList().remove(this);
    }


}
