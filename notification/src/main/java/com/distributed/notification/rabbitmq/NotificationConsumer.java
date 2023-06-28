package com.distributed.notification.rabbitmq;

import com.distributed.clients.notification.NotificationRegisterRequest;
import com.distributed.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRegisterRequest notificationRegisterRequest) {
        log.info("Consumed {} from queue", notificationRegisterRequest);
        notificationService.register(notificationRegisterRequest);
    }
}
