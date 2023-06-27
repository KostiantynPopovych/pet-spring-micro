package com.distributed.clients.notification;

public record NotificationRegisterRequest(Integer customerId, NotificationType type, String message) {
}

