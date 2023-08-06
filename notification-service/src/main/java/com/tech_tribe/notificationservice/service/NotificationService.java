package com.tech_tribe.notificationservice.service;

import com.tech_tribe.notificationservice.dto.NotificationDTO;
import com.tech_tribe.notificationservice.entity.Notification;
import com.tech_tribe.notificationservice.repository.NotificationRepository;
import com.tech_tribe.notificationservice.utils.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(NotificationDTO notificationDTO) {

        Notification notification = new Notification();
        notification.setCustomerId(notificationDTO.getCustomerId());
        notification.setMessage(notificationDTO.getMessage());
        notification.setStatus(notificationDTO.getStatus());

        notificationRepository.save(notification);

    }
}
