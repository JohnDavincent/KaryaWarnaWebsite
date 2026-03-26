package com.example.transaction_services.service;

import com.example.transaction_services.dto.CreateOrderDetailReq;
import com.example.transaction_services.dto.CreateOrderDetailRes;

import java.util.UUID;

public interface OrderDetailService {
    public CreateOrderDetailRes createOrderDetail(UUID orderId, CreateOrderDetailReq req);
}
