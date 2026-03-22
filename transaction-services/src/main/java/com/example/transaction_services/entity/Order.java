package com.example.transaction_services.entity;

import com.example.common.entity.BaseEntity;
import com.example.transaction_services.dto.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "order")
public class Order extends BaseEntity {
    @Id
    private String Id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    @Column(name = "total_discount")
    private BigDecimal totalDiscount;

    @Column(name = "status")
    private Status status;

    @OneToOne(mappedBy = "order_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList = new ArrayList<>();


}
