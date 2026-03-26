package com.example.transaction_services.repository;

import com.example.transaction_services.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(
            "SELECT o FROM Order WHERE DATE(o.createAt) = :today ORDER BY o.createAt DESC LIMIT 1"
    )
    Optional<Order> findLastOrderToday(@Param("today") LocalDate today);
}
