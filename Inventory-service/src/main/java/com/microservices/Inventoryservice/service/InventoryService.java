package com.microservices.Inventoryservice.service;

import com.microservices.Inventoryservice.dto.InventoryResponse;
import com.microservices.Inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> getStockStatus(List<String> skuCode){
    return inventoryRepository.findBySkuCodeIn(skuCode).stream()
            .map(inventory ->
                InventoryResponse.builder().skuCode(inventory.getSkuCode())
                        .status(inventory.getQuantity() > 0)
                        .build()).toList();
    }
}
