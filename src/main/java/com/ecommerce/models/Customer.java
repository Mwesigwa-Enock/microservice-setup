package com.ecommerce.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_jpa_sequence_generator")
    @SequenceGenerator(name = "customer_jpa_sequence_generator", sequenceName = "customer_id_sequence", allocationSize = 1)
    private Long customer_id;

    @Column(unique = true)
    private String email;

    private String first_name;

    private String last_name;

    private String address;

    @CreationTimestamp
    private Timestamp create_date;
}
