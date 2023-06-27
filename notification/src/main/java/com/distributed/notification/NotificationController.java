package com.distributed.notification;

import com.distributed.clients.notification.NotificationRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notifications")
@AllArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public void registerNotification(@RequestBody NotificationRegisterRequest notificationRegisterRequest) {
        notificationService.
                register(notificationRegisterRequest);
        log.info("register notification {}", notificationRegisterRequest);
    }
}
