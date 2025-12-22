package com.batch56.restaurant_service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListOfFoodItemsByRestName {

	private String name;
	private double price;
	private String imageUrl;

}
