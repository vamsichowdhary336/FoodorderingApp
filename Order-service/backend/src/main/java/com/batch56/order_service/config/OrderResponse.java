package com.batch56.order_service.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderResponse {

	private Long orderId;
	private String restaurantName;
	private String foodItemName;
	private String customerName;
	private String address;
}
