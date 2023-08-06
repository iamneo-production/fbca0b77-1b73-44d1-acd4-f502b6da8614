package com.tech_tribe.paymentservice.feignclient;

import com.tech_tribe.paymentservice.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "notification-service", url = "${api.notification-service.url}")
public interface NotificationFeignClient {
    @PostMapping("/send")
    String sendNotification(@RequestBody NotificationDTO notificationDTO);
}
