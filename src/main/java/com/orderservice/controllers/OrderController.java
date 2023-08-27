package com.orderservice.controllers;


import com.orderservice.entity.Order;
import com.orderservice.payloads.requests.CreateOrderRequest;
import com.orderservice.payloads.responses.GenericResponse;
import com.orderservice.services.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("api/")
@ApiOperation("Request Orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("orders/request")
    @ApiOperation(value = "Order Requests")
    public ResponseEntity<GenericResponse> orderRequest(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(request));
    }

    @GetMapping("order/{id}")
    @ApiOperation(value = "Get Order By Id")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findOrderById(id);
        if (order.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order Not Found!");
        }
    }


    @DeleteMapping("order/{id}")
    @ApiOperation(value = "Delete Order By Id")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        GenericResponse response = orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("order")
    @ApiOperation(value = "Update Order By Id")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        Order response = orderService.updateOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
