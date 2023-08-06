package com.tech_tribe.transactionservice.dto;

import com.tech_tribe.transactionservice.utils.TransactionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class TransactionDTO {
    private long id;
    @NotNull
    private Long customerId;
    @NotNull
    private Long paymentAmount;
    @NotNull
    private TransactionStatus status;
    @NotNull
    private String referenceId;
    private LocalDateTime createdAt;
}
