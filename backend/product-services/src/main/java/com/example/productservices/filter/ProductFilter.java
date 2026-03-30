package com.example.productservices.filter;

import com.example.common.filter.BaseFilter;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductFilter extends BaseFilter {

    private String productName;
    private UUID categoryId;
    private UUID supplierId;
    private UUID brandId;
    private Integer maxStock;
    private Integer minStock;
}
