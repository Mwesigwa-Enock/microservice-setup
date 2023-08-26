package com.ecommerce.models;

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
@Table(name = "order_locations")
public class Location {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_jpa_sequence_generator")
    @SequenceGenerator(name = "location_jpa_sequence_generator", sequenceName = "location_id_sequence", allocationSize = 1)
    private Long location_id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @CreationTimestamp
    private Timestamp create_date;
}
