package com.tech_tribe.billservice.entity;

import com.tech_tribe.billservice.dto.BillerDTO;
import com.tech_tribe.billservice.utils.BillStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "biller")
@Data
@NoArgsConstructor
public class Biller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String referenceId;
    private long customerId;
    private String type;
    private long billTotal;
    @Enumerated(EnumType.STRING)
    private BillStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public BillerDTO buildDTO(){
        return BillerDTO.builder()
                .id(this.id)
                .referenceId(this.referenceId)
                .customerId(this.customerId)
                .type(this.type)
                .billTotal(this.billTotal)
                .status(this.status)
                .createdAt(this.createdAt)
                .build();
    }

}
