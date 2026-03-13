package com.example.productservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierResponse {
    private String Supplier;
    private String contactName;
    private String phoneNumber;
    private String desc;
}
