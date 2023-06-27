package com.distributed.clients.notification;

public record NotificationRegisterRequest(Integer toCustomerId, String toCustomerEmail, String sender, NotificationType type, String message) {
}

