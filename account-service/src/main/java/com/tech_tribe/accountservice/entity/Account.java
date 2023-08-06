package com.tech_tribe.accountservice.entity;

import com.tech_tribe.accountservice.dto.AccountDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long customerId;
    private String name;
    private long accountBalance;

    public AccountDTO buildDTO() {
        return AccountDTO.builder()
                .id(this.id)
                .customerId(this.customerId)
                .name(this.name)
                .accountBalance(this.accountBalance)
                .build();
    }

}
