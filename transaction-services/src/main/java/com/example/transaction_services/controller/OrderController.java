package com.example.transaction_services.controller;

import com.example.common.dto.WebResponse;
import com.example.transaction_services.dto.CreateOrderDetailReq;
import com.example.transaction_services.dto.CreateOrderDetailRes;
import com.example.transaction_services.dto.CreateOrderResponse;
import com.example.transaction_services.entity.Order;
import com.example.transaction_services.service.OrderDetailService;
import com.example.transaction_services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/karyawarna")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    @PostMapping("/create/order")
    ResponseEntity<WebResponse<CreateOrderResponse>> createOrderForm(){
        CreateOrderResponse createOrder = orderService.createOrderForm();
        WebResponse<CreateOrderResponse> response = WebResponse.<CreateOrderResponse>builder()
                .status(HttpStatus.CREATED.value())
                .code("CREATED")
                .message("Success create order Form")
                .data(createOrder)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/{orderId}/add-items")
    ResponseEntity<WebResponse<CreateOrderDetailRes>> addItems (@PathVariable UUID orderId , @RequestBody CreateOrderDetailReq req){
        CreateOrderDetailRes createOrderDetail = orderDetailService.createOrderDetail(orderId,req);
        WebResponse<CreateOrderDetailRes> response = WebResponse.<CreateOrderDetailRes>builder()
                .status(HttpStatus.CREATED.value())
                .code("CREATED")
                .message("success add to the order with id : " + orderId)
                .data(createOrderDetail)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/finalize-order/{id}")
    ResponseEntity<WebResponse<String>> confirmOrder(@PathVariable UUID id){
        orderService.createOrderFinal(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.CREATED.value())
                .message("Success Finalize order")
                .code("FINALIZE")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/view-order")
    ResponseEntity<WebResponse<Page<CreateOrderResponse>>>viewOrder(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDateTime endDate,

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10")int size
        ){
            Page<CreateOrderResponse> getOrder = orderService.viewOrder(startDate,endDate,page,size);
            WebResponse<Page<CreateOrderResponse>> response = WebResponse.<Page<CreateOrderResponse>>builder()
                    .status(HttpStatus.OK.value())
                    .code("OK")
                    .message("Success show data")
                    .data(getOrder)
                    .build();

            return ResponseEntity.ok(response);
        }



}
