package com.example.transaction_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_number_sequence")
public class OrderNumberSequence {

    @Id
    @Column(name = "sequence_date")
    private LocalDate sequenceDate;

    @Column(name = "last_sequence")
    private Long lastSequence;


}
