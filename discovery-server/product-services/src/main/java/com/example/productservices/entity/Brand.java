package com.example.productservices.entity;

import com.example.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_brand")
public class Brand extends BaseEntity {
    @Id
    private String id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "")

}
