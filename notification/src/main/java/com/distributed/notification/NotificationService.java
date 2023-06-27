package com.distributed.notification;

import com.distributed.clients.notification.NotificationRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void register(@RequestBody NotificationRegisterRequest request) {
        Notification notification = Notification.builder()
                .type(request.type())
                .message(request.message())
                .sender(request.sender())
                .toCustomerId(request.toCustomerId())
                .sentAt(LocalDateTime.now())
                .toCustomerEmail(request.toCustomerEmail())
                .build();
        notificationRepository.save(notification);
    }
}
