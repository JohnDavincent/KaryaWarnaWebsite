package com.example.transaction_services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderResponse {
    private UUID orderId;
    private BigDecimal grandTotal;
    private BigDecimal totalDiscount;
    private Status status;
}
