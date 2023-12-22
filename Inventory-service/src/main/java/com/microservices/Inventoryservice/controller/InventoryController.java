package com.microservices.Inventoryservice.controller;

import com.microservices.Inventoryservice.dto.InventoryResponse;
import com.microservices.Inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> getStockStatus(@RequestParam List<String> skuCode) {
    return inventoryService.getStockStatus(skuCode);
    }
}
