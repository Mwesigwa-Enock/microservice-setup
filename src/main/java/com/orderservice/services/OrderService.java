package com.orderservice.services;


import com.orderservice.models.Customer;
import com.orderservice.models.Order;
import com.orderservice.models.Product;
import com.orderservice.payloads.requests.CreateOrderRequest;
import com.orderservice.payloads.responses.GenericResponse;
import com.orderservice.repositories.CustomerRepository;
import com.orderservice.repositories.OrderRepository;
import com.orderservice.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public GenericResponse createOrder(CreateOrderRequest request) {
        logger.info(">>>> Incoming order: " + request.toString());
        Optional<Customer> customer = customerRepository.findById(request.getCustomer_id());
        Optional<Product> product = productRepository.findById(request.getProduct_id());
        Order order = Order.builder()
                .customer(customer.orElse(null))
                .product(product.orElse(null))
                .shopping_cart_id(request.getShopping_cart_id())
                .status(String.valueOf(request.getStatus()))
                .build();
        orderRepository.save(order);
        return GenericResponse.builder()
                .message("Order Received")
                .status(true)
                .build();
    }

    @Override
    public GenericResponse deleteOrder(Long orderId) {
        logger.info(">>>> Delete order by Id: " + orderId);
        orderRepository.deleteById(orderId);
        return GenericResponse.builder()
                .status(true)
                .message("Order deleted successfully!")
                .build();
    }

    @Override
    public Order updateOrder(Order order) {
        logger.info(">>>> Update Order");
        orderRepository.save(order);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
