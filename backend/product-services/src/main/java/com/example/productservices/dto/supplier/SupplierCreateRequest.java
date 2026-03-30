package com.example.productservices.dto.supplier;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierCreateRequest {
    @NotBlank
    private String supplierName;

    @NotBlank
    @Size(min = 12, max = 12)
    private String phoneNumber;

    @NotBlank
    private String contactPerson;

    @Nullable
    private String desc;
}
