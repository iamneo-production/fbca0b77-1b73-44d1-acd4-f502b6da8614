package com.tech_tribe.paymentservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {
    private long id;
    private Long customerId;
    private String name;
    private Long accountBalance;
}
