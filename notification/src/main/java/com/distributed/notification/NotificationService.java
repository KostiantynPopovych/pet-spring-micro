package com.distributed.notification;

import com.distributed.clients.notification.NotificationRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void registerNotification(@RequestBody NotificationRegisterRequest request) {
        Notification notification = Notification.builder()
                .type(request.type())
                .message(request.message())
                .customerId(request.customerId())
                .build();
        notificationRepository.save(notification);
    }
}
