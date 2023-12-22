package com.microservices.Orderservice.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventoryResponse {

    private String skuCode;
    private Boolean status;
}
