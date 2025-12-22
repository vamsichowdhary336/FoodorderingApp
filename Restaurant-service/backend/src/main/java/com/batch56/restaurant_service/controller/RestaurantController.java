package com.batch56.restaurant_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch56.restaurant_service.Response.FoodItemResponse;
import com.batch56.restaurant_service.Response.ListOfFoodItemsByRestName;
import com.batch56.restaurant_service.Response.RestaurantResponse;
import com.batch56.restaurant_service.model.FoodItems;
import com.batch56.restaurant_service.model.Restaurant;
import com.batch56.restaurant_service.repo.FoodItemsRepo;
import com.batch56.restaurant_service.repo.RestaurantRepo;
import com.batch56.restaurant_service.service.RestaurantService;

@CrossOrigin(origins = { "*", "null" })
@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantservice;

	@Autowired
	RestaurantRepo restaurantrepo;

	@Autowired
	FoodItemsRepo fooditemsrepo;

	// Adding restaurant
	@PostMapping("/addRestaurant")
	public ResponseEntity<String> addRestaurant(@RequestBody Restaurant rst) {
		restaurantservice.addRestaurants(rst);
		return ResponseEntity.ok("Restaurant added successfully");
	}

	// Adding fooditem
	@PostMapping("/addItems")
	public ResponseEntity<String> addFoodItem(@RequestBody FoodItemResponse fooditemresp) {

		Restaurant restaurant = restaurantrepo.findFirstByNameIgnoreCase(fooditemresp.getRestaurantName())
				.orElseThrow(() -> new RuntimeException("Restaurant not found"));

		FoodItems food = new FoodItems();
		food.setName(fooditemresp.getName());
		food.setPrice(fooditemresp.getPrice());
		food.setImageUrl(fooditemresp.getImageUrl());
		food.setRestaurant(restaurant);

		restaurantservice.createFoodItem(food);

		return ResponseEntity.ok("Food item added successfully");
	}

//getting Restaurant by name 
	@GetMapping("by-name/{name}")
	public Restaurant getRestaurantByName(@PathVariable String name) {
		return restaurantrepo.findFirstByNameIgnoreCase(name)
				.orElseThrow(() -> new RuntimeException("Restaurant not found: " + name));
	}

	@GetMapping("/by-id/{id}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
		return restaurantrepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/by-itemId/{id}")
	public FoodItems getFoodItemById(@PathVariable Long id) {
		return fooditemsrepo.findById(id).orElseThrow(() -> new RuntimeException("Food item not found with id: " + id));
	}

	// get item by name
	@GetMapping("/by-item/{name}")
	public ResponseEntity<?> foodItemList(@PathVariable String name) {

		List<RestaurantResponse> list = restaurantservice.findByItemName(name);

		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
		}

		return ResponseEntity.ok(list);
	}

	// GET RESTAURANT LIST
	@GetMapping("/restaurantList")
	public List<Restaurant> getRestaurantList() {
		return restaurantrepo.findAll();
	}

	// Get FOOD ITEMS LIST BY RESTAURANT by NAME
	@GetMapping("/fooditemsList/by-nameOf/{restaurantName}")
	public List<ListOfFoodItemsByRestName> getItemsByRestaurantName(@PathVariable String restaurantName) {

		return restaurantservice.getItemsByRestaurantName(restaurantName);
	}

}
