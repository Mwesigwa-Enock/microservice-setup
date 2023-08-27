package com.orderservice.services;


import com.orderservice.entity.Order;
import com.orderservice.payloads.requests.CreateOrderRequest;
import com.orderservice.payloads.responses.GenericResponse;

import java.util.Optional;

public interface IOrderService {

    GenericResponse createOrder(CreateOrderRequest request);

    GenericResponse deleteOrder(Long orderId);

    Order updateOrder(Order order);

    Optional<Order> findOrderById(Long orderId);


}
