package com.example.productservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductResult {

    private String productName;
    private Integer stock;
    private String description;
    private BigDecimal sellPrice;
    private BigDecimal purchasedPrice;
    private String supplierName;
    private String categoryName;
    private LocalDateTime lastUpdate;

}
