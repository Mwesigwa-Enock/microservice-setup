package com.orderservice.services;

import com.orderservice.models.ShippingRequest;
import com.orderservice.models.ShippingResponse;

public interface IShippingService {
    ShippingResponse sendShipmentDetails(ShippingRequest request);


}
