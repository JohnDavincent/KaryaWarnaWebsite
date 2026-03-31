package com.example.transaction_services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewLastestOrder {
    private String order_number;
    private BigDecimal grand_total;
    private Status status;
    private String created_at;
    private String modified_by;
}
