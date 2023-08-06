package com.tech_tribe.paymentservice.dto;

import com.tech_tribe.paymentservice.utils.TransactionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public void assignData(AccountDTO accountDTO, BillerDTO billerDTO) {
        this.customerId = accountDTO.getCustomerId();
        this.paymentAmount = billerDTO.getBillTotal();
        this.status = TransactionStatus.Declined;
        this.referenceId = billerDTO.getReferenceId();
        this.createdAt = LocalDateTime.now();
    }

    public TransactionDTO buildDTO(TransactionStatus transactionStatus) {
        return TransactionDTO.builder()
                .customerId(this.customerId)
                .paymentAmount(this.paymentAmount)
                .status(transactionStatus)
                .referenceId(this.referenceId)
                .createdAt(this.createdAt)
                .build();
    }
}
