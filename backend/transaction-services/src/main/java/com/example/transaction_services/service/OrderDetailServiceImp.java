package com.example.transaction_services.service;

import com.example.common.exception.OrderDetailExistException;
import com.example.common.exception.OrderNotExistException;
import com.example.common.exception.ProductNotExistException;
import com.example.transaction_services.client.ProductClient;
import com.example.transaction_services.dto.CreateOrderDetailReq;
import com.example.transaction_services.dto.CreateOrderDetailRes;
import com.example.transaction_services.dto.OrderDetailProduct;
import com.example.transaction_services.entity.Order;
import com.example.transaction_services.entity.OrderDetail;
import com.example.transaction_services.repository.OrderDetailRepository;
import com.example.transaction_services.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderDetailServiceImp implements OrderDetailService {

    private final ProductClient productClient;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public CreateOrderDetailRes createOrderDetail(UUID orderId, CreateOrderDetailReq req) {

        Order existOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotExistException("Order with Id : " + orderId + " Is not exist"));

        OrderDetailProduct product = productClient.getProductDetails(req.getProductId()).getBody();

        if(product == null){
            throw new ProductNotExistException("Product with id : " + req.getProductId() + " doesn't exist");
        }

        if(orderDetailRepository.existsByOrderIdAndProductId(orderId,product.getProductId())){
            throw new OrderDetailExistException("Product already in the order list");
        }

        if(product.getStock() < req.getQuantity()){
            throw new RuntimeException("Product Stock is not enough");
        }

        BigDecimal subTotal = product.getPricePerUnit().multiply(BigDecimal.valueOf(req.getQuantity()));

        OrderDetail createOrderDetail = OrderDetail.builder()
                .productId(req.getProductId())
                .productName(product.getProductName())
                .discountPrice(req.getDiscountPrice() != null ? req.getDiscountPrice() : null)
                .quantity(req.getQuantity())
                .subTotal(subTotal)
                .oneUnitPrice(product.getPricePerUnit())
                .build();

        orderDetailRepository.save(createOrderDetail);

        if(createOrderDetail.getDiscountPrice() != null){
            existOrder.setTotalDiscount(existOrder.getTotalDiscount().add(createOrderDetail.getDiscountPrice()));
        }
        existOrder.setGrandTotal( existOrder.getGrandTotal().add(createOrderDetail.getSubTotal()));
        existOrder.addToOrderList(createOrderDetail);
        orderRepository.save(existOrder);

        return CreateOrderDetailRes.builder()
                .pricePerUnit(createOrderDetail.getOneUnitPrice())
                .quantity(createOrderDetail.getQuantity())
                .productName(createOrderDetail.getProductName())
                .discountPrice(createOrderDetail.getDiscountPrice())
                .subTotal(createOrderDetail.getSubTotal())
                .build();

    }



}
