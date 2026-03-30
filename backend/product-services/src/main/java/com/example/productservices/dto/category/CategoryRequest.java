package com.example.productservices.dto.category;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    @NotBlank
    private String categoryName;

    @NotBlank
    private String categoryCode;

}
