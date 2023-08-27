package com.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_jpa_sequence_generator")
    @SequenceGenerator(name = "product_jpa_sequence_generator", sequenceName = "location_id_sequence", allocationSize = 1)
    private Long product_id;

    private String product_name;

    private  String product_description;

    private String location;

    @CreationTimestamp
    private Timestamp create_date;
}
