package com.tech_tribe.accountservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {
    private long id;
    @NotNull
    private Long customerId;
    @NotNull
    private String name;
    @NotNull
    private Long accountBalance;
}
