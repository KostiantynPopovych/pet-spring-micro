package com.distributed.clients.notification;

import java.time.LocalDateTime;

public record NotificationRegisterRequest(Integer toCustomerId, String toCustomerEmail, String sender, NotificationType type, String message) {
}

