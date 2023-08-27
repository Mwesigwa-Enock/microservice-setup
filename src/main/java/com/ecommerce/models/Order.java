package com.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_jpa_sequence_generator")
    @SequenceGenerator(name = "order_jpa_sequence_generator", sequenceName = "order_id_sequence", allocationSize = 1)
    private Long order_id;
}
