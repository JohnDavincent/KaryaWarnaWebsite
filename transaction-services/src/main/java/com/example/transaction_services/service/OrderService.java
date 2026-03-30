package com.example.transaction_services.service;

import com.example.common.dto.WebResponse;
import com.example.transaction_services.dto.CreateOrderResponse;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderService {

    public CreateOrderResponse createOrderForm();
    public void createOrderFinal(UUID orderId);
    public Page<CreateOrderResponse> viewOrder(LocalDateTime timeStart, LocalDateTime timeEnd, int page, int size);
}
