package com.example.transaction_services.service;

import com.example.common.dto.WebResponse;
import com.example.common.exception.OrderNotExistException;
import com.example.transaction_services.entity.Order;
import com.example.transaction_services.entity.OrderDetail;
import com.example.transaction_services.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public void createOrderForm() {
        Order newOrder = Order.builder()
                .orderDetailList(new ArrayList<>())
                .build();
        orderRepository.save(newOrder);
    }

    @Transactional
    @Override
    public void createOrderFinal(UUID orderId) {
        Order existOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotExistException("Order with id " + orderId + " is not exist!"));
        LocalDate date = LocalDate.now();
        String setDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        int sequence = 1;

        Optional<Order> checkLastOrder = orderRepository.findLastOrderToday(date);
        if(checkLastOrder.isPresent()){
            String lastOrderNumber = checkLastOrder.get().getOrderNumber();
            int getNumber = Integer.parseInt(lastOrderNumber.split("-")[3]);
            sequence = getNumber + 1;
        }
        String newOrderNumber = setDate + "-" + sequence;
        existOrder.setOrderNumber(newOrderNumber);

    }
}
