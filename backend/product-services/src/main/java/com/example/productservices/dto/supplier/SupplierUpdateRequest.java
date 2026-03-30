package com.example.productservices.dto.supplier;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierUpdateRequest {
    @Nullable
    private String supplierName;

    @Nullable
    @Size(min = 12, max = 12)
    private String phoneNumber;

    @Nullable
    private String contactPerson;

    @Nullable
    private String desc;
}
