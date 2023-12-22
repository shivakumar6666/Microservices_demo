package com.microservices.Orderservice.service;

import com.microservices.Orderservice.dto.InventoryResponse;
import com.microservices.Orderservice.dto.OrderLineItemsDto;
import com.microservices.Orderservice.dto.OrderRequest;
import com.microservices.Orderservice.model.Order;
import com.microservices.Orderservice.model.OrderLineItems;
import com.microservices.Orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {
    ResponseEntity<String> response;
    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();
        order.setOrderLineItemsList(orderLineItems);


        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(orderLineItem -> orderLineItem.getSkuCode()).toList();


        InventoryResponse[] inventoryResponses = webClient.build().get()
                .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean productaAvailable = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.getStatus());


        if(productaAvailable){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product currently not available , Kindly try again later...");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;

    }

    public List<Order> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
    return allOrders;
    }
}
