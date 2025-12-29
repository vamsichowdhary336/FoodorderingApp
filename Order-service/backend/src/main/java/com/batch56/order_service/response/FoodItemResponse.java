package com.batch56.order_service.response;

import lombok.Data;

@Data
public class FoodItemResponse {

	private Long id;
	private String name;
	private Double price;
	private String imageUrl;
}
