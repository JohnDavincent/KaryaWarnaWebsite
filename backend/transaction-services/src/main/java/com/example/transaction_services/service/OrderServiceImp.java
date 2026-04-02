package com.example.transaction_services.service;

import com.example.common.exception.OrderNotExistException;
import com.example.transaction_services.client.ProductClient;
import com.example.transaction_services.dto.CreateOrderResponse;
import com.example.transaction_services.dto.Status;
import com.example.transaction_services.dto.ViewLastestOrder;
import com.example.transaction_services.entity.Order;
import com.example.transaction_services.projection.ProductStockProjection;
import com.example.transaction_services.repository.OrderDetailRepository;
import com.example.transaction_services.repository.OrderNumberSequenceRepository;
import com.example.transaction_services.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderNumberSequenceRepository orderNumberSequenceRepository;
    private final ProductClient productClient;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd-MM-yyyy");

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
        List<ProductStockProjection> stockProjections = orderDetailRepository.getStock(existOrder.getId());
        Map<UUID, Integer> map = stockProjections.stream()
                //collect --> you want to serve the data as what
                .collect(Collectors.toMap(
                        ProductStockProjection::getProductId,
                        ProductStockProjection::getQuantity
                ));

        productClient.updateStock(map);
        existOrder.setOrderNumber(orderNumber);
        existOrder.setUpdateAt((today.atStartOfDay()));
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


    @Override
    public List<ViewLastestOrder> getLatestOrder() {

        return orderRepository.viewLastCreatedOrder().stream()
                .map(order -> {
                    return ViewLastestOrder.builder()
                            .order_number(order.getOrderNumber())
                            .created_at(order.getCreatedAt().format(formatter))
                            .grand_total(formatInRp(order.getGrandTotal()))
                            .modified_by(order.getLastModifiedBy())
                            .status(order.getStatus())
                            .build();
                }).toList();
    }


    public String formatInRp(BigDecimal amount){
        Locale indo = Locale.of("id","ID");
        NumberFormat formatMoney = NumberFormat.getCurrencyInstance(indo);
        formatMoney.setMaximumFractionDigits(0);
        return formatMoney.format(amount);

    }


}
