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
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "order")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_number")
    private String orderNumber;


    @Column(name = "grand_total")
    private BigDecimal grandTotal;

    @Column(name = "total_discount")
    private BigDecimal totalDiscount;

    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public void addToOrderList(OrderDetail detail){
        this.orderDetailList.add(detail);
        detail.setOrder(this);
    }

}
