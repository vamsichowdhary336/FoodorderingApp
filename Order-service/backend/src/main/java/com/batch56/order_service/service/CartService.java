package com.batch56.order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch56.order_service.model.Cart;
import com.batch56.order_service.model.Order;
import com.batch56.order_service.repo.CartRepo;
import com.batch56.order_service.repo.OrderRepo;

public class CartService {
	@Service
	public class OrderService {

		@Autowired
		private CartRepo cartRepo;

		@Autowired
		private OrderRepo orderRepo;

		// PLACE ORDER FROM CART
		public String placeOrder(String customerName) {

			List<Cart> cartItems = cartRepo.findByCustomerName(customerName);

			if (cartItems.isEmpty()) {
				return "Cart is empty";
			}

			for (Cart cart : cartItems) {
				Order order = new Order();
				order.setCustomerName(cart.getCustomerName());
				order.setRestaurantId(cart.getRestaurantId());
				order.setFoodItemId(cart.getFoodItemId());
				order.setFoodItemName(cart.getFoodItemName());
				order.setPrice(cart.getPrice());
				order.setImageUrl(cart.getImageUrl());

				orderRepo.save(order);
			}

			// clear cart after order
			cartRepo.deleteAll(cartItems);

			return "Order placed successfully";
		}
	}
}
