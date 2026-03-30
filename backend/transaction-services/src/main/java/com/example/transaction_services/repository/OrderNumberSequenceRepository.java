package com.example.transaction_services.repository;

import com.example.transaction_services.entity.OrderNumberSequence;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderNumberSequenceRepository extends JpaRepository<OrderNumberSequence, LocalDate> {

    @Query(
            value = "INSERT INTO transaction_services.order_number_sequence (sequence_date, last_sequence)" +
                    "Values (:seqDate,1) " +
                    "On Conflict (sequence_date) " +
                    "DO UPDATE " +
                    "SET last_sequence = transaction_services.order_number_sequence.last_sequence + 1 " +
                    "RETURNING last_sequence",
            nativeQuery = true
    )
    Long getLastSequence(@Param("seqDate")LocalDate today);
}
