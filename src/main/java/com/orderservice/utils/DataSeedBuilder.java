package com.orderservice.utils;

import com.orderservice.entity.Customer;
import com.orderservice.entity.Product;
import com.orderservice.repositories.CustomerRepository;
import com.orderservice.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataSeedBuilder {

    private static final Logger logger = LoggerFactory.getLogger(DataSeedBuilder.class);

    @Bean
    CommandLineRunner seedDatabase(ProductRepository productRepository, CustomerRepository customerRepository) {
        return args -> {
            logger.info("=======================Preloading Customer data=================================");
            Customer enock = Customer.builder()
                    .address("Mengo")
                    .email("enockmwesigwa7@gmail.com")
                    .first_name("Enock")
                    .last_name("Mwesigwa")
                    .build();

            Customer testUser = Customer.builder()
                    .address("Kampala")
                    .email("testuser@gmail.com")
                    .first_name("Test")
                    .last_name("User")
                    .build();

            Customer testUser1 = Customer.builder()
                    .address("Bugolobi")
                    .email("testuser1@gmail.com")
                    .first_name("Test1")
                    .last_name("User1")
                    .build();
            List<Customer> customerList = Arrays.asList(enock, testUser1, testUser);
            Iterable<Customer> customers = customerRepository.saveAll(customerList);
           // Iterable<Customer> customers = customerRepository.findAll();
            customers.forEach(System.out::println);

            logger.info("=======================Preloading Products data=================================");
            Product laptop = Product.builder()
                    .product_name("Laptop")
                    .product_description("Brand new HP laptop")
                    .location("City Center")
                    .build();

            Product blender = Product.builder()
                    .product_name("Blender")
                    .product_description("Juice Blender")
                    .location("Gayaza")
                    .build();

            Product iphone15 = Product.builder()
                    .product_name("Iphone")
                    .product_description("Brand new Iphone 15")
                    .location("Kooki Towers")
                    .build();

            Product camera = Product.builder()
                    .product_name("Camera")
                    .product_description("Brand Canon 15 Camera")
                    .location("Rwenzori Towers")
                    .build();

            List<Product> productList = Arrays.asList(laptop, blender, iphone15, camera);
            productRepository.saveAll(productList);
            Iterable<Product> products = productRepository.findAll();
            products.forEach(System.out::println);
        };
    }


}
