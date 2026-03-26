package com.example.transaction_services.service;

import com.example.common.dto.WebResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OrderService {

    public void createOrderForm();
    public void createOrderFinal(UUID orderId);
}
