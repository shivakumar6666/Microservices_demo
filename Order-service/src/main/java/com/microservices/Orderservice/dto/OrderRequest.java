package com.microservices.Orderservice.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;

}
