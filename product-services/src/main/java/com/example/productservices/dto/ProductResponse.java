package com.example.productservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductResponse {
    private String name;
    private String sellPrice;
    private String purchasePrice;
    private String description;
    private Integer stock;
    private LocalDateTime createAt;
}
