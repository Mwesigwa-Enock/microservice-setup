package com.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer_orders")
public class Order {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_jpa_sequence_generator")
    @SequenceGenerator(name = "order_jpa_sequence_generator", sequenceName = "order_id_sequence", allocationSize = 1)
    private Long order_id;

    private Long shopping_cart_id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private String status;

    @CreationTimestamp
    private Timestamp create_date;
}
