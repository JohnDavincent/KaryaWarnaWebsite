package com.example.transaction_services.repository;

import com.example.transaction_services.entity.OrderDetail;
import com.example.transaction_services.projection.ProductStockProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    Optional<OrderDetail> findByOrderId(UUID id);
    boolean existsById(UUID id);
    boolean existsByProductId(UUID productId);
    boolean existsByOrderIdAndProductId(UUID orderId, UUID productId);

    @Query(
           value = """
                    SELECT o.product_id AS productId, o.quantity AS quantity 
                    FROM transaction_services.order_detail o
                    WHERE o.order_id = :orderId
                 """,
            nativeQuery = true
    )
    List<ProductStockProjection> getStock(@Param("orderId") UUID orderId);
}
