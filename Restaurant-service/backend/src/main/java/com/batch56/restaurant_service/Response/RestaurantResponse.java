package com.batch56.restaurant_service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantResponse {

	private Long id;
	private String name;
	private Double price;
	private String imageUrl;

	private String restaurantName;
	private String restaurantLocation;
}
