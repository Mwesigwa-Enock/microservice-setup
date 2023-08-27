package com.orderservice.services;

import com.orderservice.models.NotificationRequest;
import com.orderservice.models.NotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService implements INotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    @Value("${notification.service}")
    String baseUrl;


    @Override
    public NotificationResponse sendNotification(NotificationRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationRequest> notificationRequest = new HttpEntity<>(
                request);
        String notificationUrl = baseUrl + "/notifications/send";
        NotificationResponse response = restTemplate.postForObject(notificationUrl, notificationRequest, NotificationResponse.class);
        logger.info(">>>> Order Notification sent!");
        return response;
    }
}
