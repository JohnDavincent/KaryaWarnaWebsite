package com.example.productservices.filter;

import com.example.common.filter.BaseFilter;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductFilter extends BaseFilter {

    private String productName;
    private Long categoryId;
    private Long supplierId;
    private Integer maxStock;
    private Integer minStock;
}
