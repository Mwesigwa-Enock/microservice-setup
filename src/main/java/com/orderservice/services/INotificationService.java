package com.orderservice.services;

import com.orderservice.models.NotificationRequest;
import com.orderservice.models.NotificationResponse;

public interface INotificationService {
    NotificationResponse sendNotification(NotificationRequest request);
}
