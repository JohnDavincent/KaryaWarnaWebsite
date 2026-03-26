package com.example.transaction_services.controller;

import com.example.common.dto.WebResponse;
import com.example.transaction_services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/karyawarna/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create/order")
    ResponseEntity<WebResponse<String>> createOrderForm(){
        orderService.createOrderForm();
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.CREATED.value())
                .code("CREATED")
                .message("Success create order Form")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    ResponseEntity<WebResponse<String>> confirmOrder()

}
