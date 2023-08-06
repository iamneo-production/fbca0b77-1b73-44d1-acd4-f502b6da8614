package com.tech_tribe.paymentservice.dto;

import com.tech_tribe.paymentservice.utils.NotificationType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private long id;

    private Long customerId;

    private String message;

    private NotificationType status;

    private LocalDateTime createdAt;

}