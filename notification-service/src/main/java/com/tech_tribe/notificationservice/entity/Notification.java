package com.tech_tribe.notificationservice.entity;

import com.tech_tribe.notificationservice.utils.NotificationType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long customerId;
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType status;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
