package com.tech_tribe.notificationservice.controller;

import com.tech_tribe.notificationservice.dto.NotificationDTO;
import com.tech_tribe.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity sendNotification(@Valid @RequestBody NotificationDTO notificationDTO) {
        try {
            notificationService.sendNotification(notificationDTO);
            return ResponseEntity.ok("Notification sent!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
