package com.orderservice.services;


import com.orderservice.models.ShippingRequest;
import com.orderservice.models.ShippingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShippingService implements IShippingService{

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    @Value("${shipping.service}")
    String baseUrl;

    @Override
    public ShippingResponse sendShipmentDetails(ShippingRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ShippingRequest> shippingRequest = new HttpEntity<>(
                request);
        String notificationUrl = baseUrl + "/api/shipping/receive";
        ShippingResponse response = restTemplate.postForObject(notificationUrl, shippingRequest, ShippingResponse.class);
        logger.info(">>>> Order Shipment sent!");
        return response;
    }
}
