package com.tech_tribe.paymentservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDTO {
    @NotNull
    private Long customerId;
    @NotNull
    private String referenceId;
}
