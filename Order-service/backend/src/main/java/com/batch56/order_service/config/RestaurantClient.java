package com.batch56.order_service.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.batch56.order_service.response.FoodItems;
import com.batch56.order_service.response.Restaurant;

@FeignClient(name = "restaurant-service", url = "http://localhost:8282")
public interface RestaurantClient {

	// get restaurant by name
	@GetMapping("/api/v1/restaurant/by-name/{name}")
	Restaurant getRestaurantByName(@PathVariable String name);

	// get restaurant by id
	@GetMapping("/api/v1/restaurant/by-id/{id}")
	Restaurant getRestaurantById(@PathVariable Long id);

	// get food item by id
	@GetMapping("/api/v1/restaurant/by-itemId/{id}")
	FoodItems getFoodItemById(@PathVariable Long id);

	// get food item(s) by name
	@GetMapping("/api/v1/restaurant/by-item/{name}")
	Object getFoodItemByName(@PathVariable String name);

}
