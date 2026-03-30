package com.example.productservices.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandUpdateResponse {
    private String brandName;
    private String brandCode;
    private LocalDateTime UpdateAt;
    private String UpdateBy;
    private String supplierName;
}
