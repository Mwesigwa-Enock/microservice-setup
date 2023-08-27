package com.orderservice.services;


import com.orderservice.entity.Customer;
import com.orderservice.entity.Order;
import com.orderservice.entity.Product;
import com.orderservice.models.NotificationRequest;
import com.orderservice.models.NotificationResponse;
import com.orderservice.models.ShippingRequest;
import com.orderservice.models.ShippingResponse;
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

    private final ShippingService shippingService;
    private final NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository, ShippingService shippingService, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.shippingService = shippingService;
        this.notificationService = notificationService;
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
        Order created = orderRepository.save(order);
        //Orchestration of the service calls
        //send shipment
        ShippingRequest shippingRequest = ShippingRequest.builder()
                .order_id(created.getOrder_id())
                .customer_id(created.getCustomer().getCustomer_id())
                .build();


        ShippingResponse shippingResponse = shippingService.sendShipmentDetails(shippingRequest);
        logger.info("Shipping Response: " + shippingResponse);

        //send Notification
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .customer_id(created.getCustomer().getCustomer_id())
                .message("Order completed")
                .build();
        NotificationResponse notificationResponse = notificationService.sendNotification(notificationRequest);
        logger.info("Notification Response: " + notificationResponse);


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
