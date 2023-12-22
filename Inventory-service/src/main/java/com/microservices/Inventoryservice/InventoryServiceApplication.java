package com.microservices.Inventoryservice;

import com.microservices.Inventoryservice.model.Inventory;
import com.microservices.Inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadInventory(InventoryRepository repository){
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("DELLPC");
			inventory1.setQuantity(10);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("HPPC");
			inventory2.setQuantity(0);


			repository.save(inventory1);
			repository.save(inventory2);
		};
	}
}
