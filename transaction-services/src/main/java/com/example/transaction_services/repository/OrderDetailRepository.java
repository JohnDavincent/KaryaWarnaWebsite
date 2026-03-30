package com.example.transaction_services.repository;

import com.example.transaction_services.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    Optional<OrderDetail> findByOrderId(UUID id);
    boolean existsById(UUID id);
    boolean existsByProductId(UUID productId);
    boolean existsByOrderIdAndProductId(UUID orderId, UUID productId);
}
