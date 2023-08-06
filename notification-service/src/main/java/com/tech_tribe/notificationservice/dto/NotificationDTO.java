package com.tech_tribe.notificationservice.dto;

import com.tech_tribe.notificationservice.utils.NotificationType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class NotificationDTO {
    private long id;
    @NotNull
    private Long customerId;
    @NotNull
    private String message;
    @NotNull
    private NotificationType status;

    private LocalDateTime createdAt;
}
