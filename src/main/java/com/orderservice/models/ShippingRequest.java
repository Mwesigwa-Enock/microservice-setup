package com.orderservice.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingRequest {
    private Long order_id;
    private Long customer_id;
}
