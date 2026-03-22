package com.example.productservices.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierResponse {
    private String supplierName;
    private String contactName;
    private String phoneNumber;
    private String desc;
}
