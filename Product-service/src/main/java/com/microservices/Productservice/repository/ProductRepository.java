package com.microservices.Productservice.repository;

import com.microservices.Productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

}

