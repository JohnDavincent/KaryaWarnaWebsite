package com.example.productservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {
    private String supplierName;
    private String phoneNumber;
    private String contactPerson;
    private String desc;
}
