package com.example.productservices.dto.product;

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

public class ProductCreateRequest {
    @NotBlank
    private String productName;

    @NotNull
    @Max(100000000)
    private BigDecimal sellPrice;

    @NotNull
    @Max(100000000)
    private BigDecimal purchasePrice;

    @Nullable
    @Size(min = 5 , max = 100 )
    private String description;

    @NotNull
    @Positive
    private Integer stock;

    @NotBlank
    private String category;

    @NotBlank
    private String supplier;

    @NotBlank
    private String brand;

    private LocalDateTime createAt;
}
