package com.example.productservices.entity;

import com.example.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String productName;

    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;

    @Column(name = "description")
    private String description;

    @Column(nullable = false, name = "purchase_price")
    private BigDecimal purchasePrice;

    @Column(nullable = false, name = "sell_price")
    private BigDecimal sellPrice;

    @Column(nullable = false, name = "stock")
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @ManyToMany
    @JoinTable(
            name = "product_brand",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    @Builder.Default
    @JsonIgnore
    private List<Brand> brandList = new ArrayList<>();

    //add method
    public void addBrandToList(Brand brand){
        this.brandList.add(brand);
        brand.getProductList().add(this);
    }

    //delete method
    public void deleteBrandFromList(Brand brand){
        this.getBrandList().remove(brand);
        brand.getProductList().remove(this);
    }

}

