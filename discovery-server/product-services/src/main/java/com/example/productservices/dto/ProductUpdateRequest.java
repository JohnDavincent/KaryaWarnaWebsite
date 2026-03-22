package com.example.productservices.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
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
public class ProductUpdateRequest {
    @Nullable
    private String productName;

    @Nullable
    @Max(100000000)
    private BigDecimal sellPrice;

    @Nullable
    @Max(100000000)
    private BigDecimal purchasePrice;

    @Nullable
    @Size(min = 5 , max = 100 )
    private String description;

    @Nullable
    @Positive
    private Integer stock;

    @Nullable
    private Long categoryId;

    @Nullable
    private Long supplierId;

}
