package com.microservices.Productservice.controller;


import com.microservices.Productservice.dto.ProductRequest;
import com.microservices.Productservice.dto.ProductResponse;
import com.microservices.Productservice.model.Product;
import com.microservices.Productservice.repository.ProductRepository;
import com.microservices.Productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
    productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
