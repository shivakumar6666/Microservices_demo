package com.microservices.Orderservice.controller;

import com.microservices.Orderservice.dto.OrderRequest;
import com.microservices.Orderservice.model.Order;
import com.microservices.Orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")

    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
//        log.info("Cannot Place Order Executing Fallback logic");
        return "Oops! Something went wrong, please order after some time!";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Order> getAllOrders (){
        List<Order> allOrders = orderService.getAllOrders();
        return allOrders;
    }
}
