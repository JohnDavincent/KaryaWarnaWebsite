package com.example.transaction_services.service;

import com.example.common.dto.WebResponse;
import com.example.common.exception.OrderNotExistException;
import com.example.transaction_services.dto.CreateOrderResponse;
import com.example.transaction_services.dto.Status;
import com.example.transaction_services.entity.Order;
import com.example.transaction_services.entity.OrderDetail;
import com.example.transaction_services.entity.OrderNumberSequence;
import com.example.transaction_services.repository.OrderNumberSequenceRepository;
import com.example.transaction_services.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderNumberSequenceRepository orderNumberSequenceRepository;

    @Override
    public CreateOrderResponse createOrderForm() {
        Order newOrder = Order.builder()
                .orderDetailList(new ArrayList<>())
                .grandTotal(BigDecimal.ZERO)
                .totalDiscount(BigDecimal.ZERO)
                .status(Status.DRAFT)
                .build();

        Order createOrder = orderRepository.save(newOrder);

        return CreateOrderResponse.builder()
                .orderId(createOrder.getId())
                .status(createOrder.getStatus())
                .grandTotal(createOrder.getGrandTotal())
                .totalDiscount(createOrder.getTotalDiscount())
                .build();
    }

    @Transactional
    @Override
    public void createOrderFinal(UUID orderId) {
        Order existOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotExistException("Order with id " + orderId + " is not exist!"));

        if(existOrder.getOrderDetailList() == null || existOrder.getOrderDetailList().isEmpty()){
            throw new RuntimeException("Order cannot be finalize because there is no item");
        }
        LocalDate today = LocalDate.now();
        String formatToday  = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        long sequenceNumber;
        /*Optional<OrderNumberSequence> sequence = orderNumberSequenceRepository.findById(today);
        if(sequence.isPresent()){
            OrderNumberSequence getSequence = sequence.get();
            sequenceNumber = getSequence.getLastSequence() + 1;
            getSequence.setLastSequence(sequenceNumber);
            orderNumberSequenceRepository.save(getSequence);
        }else{
            OrderNumberSequence newSequence = new OrderNumberSequence();
            newSequence.setSequenceDate(today);
            newSequence.setLastSequence(1L);
            orderNumberSequenceRepository.save(newSequence);
            sequenceNumber = 1L;
        }*/

        long lastSequenceNumber = orderNumberSequenceRepository.getLastSequence(today);

        String orderNumber = formatToday + "-" + lastSequenceNumber;

        existOrder.setOrderNumber(orderNumber);
        existOrder.setStatus(Status.SUCCESS);

    }

    @Override
    public Page<CreateOrderResponse> viewOrder(LocalDateTime timeStart, LocalDateTime timeEnd, int page, int size) {
        if(timeStart == null && timeEnd == null){
            LocalDate today = LocalDate.now();
            timeStart = today.atStartOfDay();
            timeEnd = today.plusDays(1).atStartOfDay();
        }else if(timeEnd == null){
            timeEnd = timeStart.plusDays(1);
        }else if(timeStart == null){
            timeStart = timeEnd.minusDays(7);
        }

        Pageable pageable = PageRequest.of(page,size);
        return orderRepository.findOrderByCreatedAt(timeStart,timeEnd,pageable)
                .map(order -> {
                    return CreateOrderResponse.builder()
                            .totalDiscount(order.getTotalDiscount())
                            .grandTotal(order.getGrandTotal())
                            .status(order.getStatus())
                            .orderId(order.getId())
                            .build();
                });
    }
}
