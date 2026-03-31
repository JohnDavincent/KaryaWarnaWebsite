package com.example.transaction_services.repository;

import com.example.transaction_services.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(
           value = """
                SELECT *
                FROM transaction_services."order" o
                WHERE o.created_at Between :startDate AND :endDate
                ORDER BY o.created_at DESC                             
                """,
            countQuery = """
               SELECT COUNT(*)
               FROM "order" o
               WHERE o.created_at >= :startDate
               AND o.created_at <= :endDate
            """,
            nativeQuery = true

    )
    Page<Order> findOrderByCreatedAt(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    @Query(
            value = """
                SELECT *
                FROM transaction_services."order" o
                ORDER BY o.created_at DESC           
                LIMIT 5
            """,
            nativeQuery = true
    )
    List<Order> viewLastCreatedOrder();
}
