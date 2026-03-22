package com.example.productservices.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductResponse {
    private String productName;
    private BigDecimal sellPrice;
    private BigDecimal purchasePrice;
    private String description;
    private Integer stock;
    private String category;
    private String supplier;
    private String id;
    private LocalDateTime createAt;
}
