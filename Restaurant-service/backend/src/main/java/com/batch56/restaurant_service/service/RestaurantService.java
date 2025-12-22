package com.batch56.restaurant_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch56.restaurant_service.Response.ListOfFoodItemsByRestName;
import com.batch56.restaurant_service.Response.RestaurantResponse;
import com.batch56.restaurant_service.model.FoodItems;
import com.batch56.restaurant_service.model.Restaurant;
import com.batch56.restaurant_service.repo.FoodItemsRepo;
import com.batch56.restaurant_service.repo.RestaurantRepo;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepo restaurantrepo;

	@Autowired
	private FoodItemsRepo fooditemsrepo;

	// add restaurant
	public Restaurant addRestaurants(Restaurant rst) {
		return restaurantrepo.save(rst);
	}

	// add food item
	public FoodItems createFoodItem(FoodItems fooditems) {
		return fooditemsrepo.save(fooditems);
	}

	// Search restaurants by FOOD ITEM NAME
	public List<RestaurantResponse> findByItemName(String itemName) {

		List<FoodItems> items = fooditemsrepo.findByNameIgnoreCase(itemName);

		return items
				.stream().map(item -> new RestaurantResponse(item.getId(), item.getName(), item.getPrice(),
						item.getImageUrl(), item.getRestaurant().getName(), item.getRestaurant().getLocation()))
				.toList();
	}

	// Get all food items by RESTAURANT NAME
	public List<ListOfFoodItemsByRestName> getItemsByRestaurantName(String restaurantName) {

		return fooditemsrepo.findByRestaurantName(restaurantName).stream()
				.map(item -> new ListOfFoodItemsByRestName(item.getName(), item.getPrice(), item.getImageUrl()))
				.toList();
	}
}
