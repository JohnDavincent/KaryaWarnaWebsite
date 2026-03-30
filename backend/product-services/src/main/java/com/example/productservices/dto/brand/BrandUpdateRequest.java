package com.example.productservices.dto.brand;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BrandUpdateRequest {
    @NotBlank
    @Size(min = 1, max = 30)
    private String brandName;

    @NotNull
    private String brandCode;

}
