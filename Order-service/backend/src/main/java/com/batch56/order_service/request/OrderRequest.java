package com.batch56.order_service.request;

import lombok.Data;

@Data
public class OrderRequest {

	private Long restaurantId;
	private Long foodItemId;

	private String customerName;
	private String address;
}
