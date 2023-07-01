package com.distributed.notification.kafka;

import com.distributed.clients.notification.NotificationRegisterRequest;
import com.distributed.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationKafkaConsumer {
    private final NotificationService notificationService;

    @KafkaListener(
            topics = "notification",
            groupId = "notification-group-1"
    )
    public void consumer(NotificationRegisterRequest registerRequest) {
        log.info("Consumed {} from queue", registerRequest);
        notificationService.register(registerRequest);
    }
}
