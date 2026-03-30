package com.example.productservices.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailProduct {

    private UUID productId;
    private String productName;
    private BigDecimal pricePerUnit;
    private Integer stock;
}
