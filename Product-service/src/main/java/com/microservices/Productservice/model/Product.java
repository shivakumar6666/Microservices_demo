package com.microservices.Productservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "PRODUCT_TABLE")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    private BigDecimal price;
}
