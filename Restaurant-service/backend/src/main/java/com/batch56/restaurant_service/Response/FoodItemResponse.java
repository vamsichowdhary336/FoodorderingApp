package com.batch56.restaurant_service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemResponse {

	private String name;
	private double price;
	private String imageUrl;
	private String restaurantName;
}
