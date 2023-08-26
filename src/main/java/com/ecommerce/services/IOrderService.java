package com.ecommerce.services;



import com.ecommerce.models.Order;
import com.ecommerce.payloads.requests.CreateOrderRequest;
import com.ecommerce.payloads.responses.GenericResponse;

import java.util.Optional;

public interface IOrderService {

    GenericResponse createOrder(CreateOrderRequest request);

    GenericResponse deleteOrder(Long orderId);

    Order updateOrder(Order order);

    Optional<Order> findOrderById(Long orderId);


}
