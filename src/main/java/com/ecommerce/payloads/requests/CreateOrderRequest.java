package com.ecommerce.payloads.requests;

import com.ecommerce.utils.Constants;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
public class CreateOrderRequest {

    private Long shopping_cart_id;

    private Long customer_id;

    private Long product_id;

    @Enumerated(EnumType.STRING)
    private Constants.Status status;
}
