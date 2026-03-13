package com.example.productservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductRequest {
    private String productName;
    private String description;
    private BigDecimal purchasePrice;
    private Integer stock;
    private BigDecimal sellPrice;
    private String supplier;
    private String category;
}
