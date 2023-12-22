package com.microservices.Productservice.service;

import com.microservices.Productservice.dto.ProductRequest;
import com.microservices.Productservice.dto.ProductResponse;
import com.microservices.Productservice.model.Product;
import com.microservices.Productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product= Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} created successfully",product.getId());
    }


    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
       return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
       return ProductResponse.builder()
                .name(product.getName())
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
