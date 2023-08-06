package com.tech_tribe.transactionservice.entity;

import com.tech_tribe.transactionservice.dto.TransactionDTO;
import com.tech_tribe.transactionservice.utils.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long customerId;
    private long paymentAmount;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private String referenceId;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public TransactionDTO build() {
        return TransactionDTO.builder()
                .id(this.id)
                .customerId(this.customerId)
                .paymentAmount(this.paymentAmount)
                .status(this.status)
                .referenceId(this.referenceId)
                .createdAt(this.createdAt)
                .build();
    }

}
