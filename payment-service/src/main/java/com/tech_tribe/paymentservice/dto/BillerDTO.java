package com.tech_tribe.paymentservice.dto;

import com.tech_tribe.paymentservice.utils.BillStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BillerDTO {

    private long id;
    private String referenceId;

    @NotNull
    private Long customerId;
    @NotNull
    private String type;
    @NotNull
    private Long billTotal;

    private BillStatus status;
    private LocalDateTime createdAt;
}
