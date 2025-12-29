package com.batch56.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch56.order_service.config.RestaurantClient;
import com.batch56.order_service.model.Cart;
import com.batch56.order_service.model.Order;
import com.batch56.order_service.repo.OrderRepo;
import com.batch56.order_service.request.OrderRequest;
import com.batch56.order_service.response.FoodItemResponse;
import com.batch56.order_service.response.OrderResponse;
import com.batch56.order_service.response.Restaurant;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private RestaurantClient restaurantClient;

	// Add Order
	public Order placeOrder(OrderRequest request) {

		Order order = new Order();
		order.setRestaurantId(request.getRestaurantId());
		order.setFoodItemId(request.getFoodItemId());
		order.setCustomerName(request.getCustomerName());
		order.setAddress(request.getAddress());

		return orderRepo.save(order);
	}

	// Get Order Details
	public OrderResponse getOrderDetails(Long orderId) {

		Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		Restaurant restaurant = restaurantClient.getRestaurantById(order.getRestaurantId());

		FoodItemResponse foodItem = restaurantClient.getFoodItemById(order.getFoodItemId());

		return new OrderResponse(order.getId(), restaurant.getName(), foodItem.getName(), order.getCustomerName(),
				order.getAddress());
	}

	public Order placeOrderFromCart(Cart cart, String address) {

		Order order = new Order();
		order.setRestaurantId(cart.getRestaurantId());
		order.setFoodItemId(cart.getFoodItemId());
		order.setCustomerName(cart.getCustomerName());
		order.setAddress(address);

		return orderRepo.save(order);
	}

	public OrderResponse getLatestOrder(String customerName) {

		Order order = orderRepo.findFirstByCustomerNameOrderByIdDesc(customerName)
				.orElseThrow(() -> new RuntimeException("No orders found for customer: " + customerName));

		Restaurant restaurant = restaurantClient.getRestaurantById(order.getRestaurantId());

		FoodItemResponse foodItem = restaurantClient.getFoodItemById(order.getFoodItemId());

		return new OrderResponse(order.getId(), restaurant.getName(), foodItem.getName(), order.getCustomerName(),
				order.getAddress());
	}

}
