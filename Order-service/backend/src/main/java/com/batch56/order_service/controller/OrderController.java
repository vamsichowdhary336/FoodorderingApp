package com.batch56.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch56.order_service.model.Cart;
import com.batch56.order_service.model.Order;
import com.batch56.order_service.repo.CartRepo;
import com.batch56.order_service.request.OrderRequest;
import com.batch56.order_service.response.OrderResponse;
import com.batch56.order_service.service.OrderService;

@CrossOrigin(origins = { "*", "null" })
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartRepo cartRepo;

	@PostMapping("/addorder")
	public Order addOrder(@RequestBody OrderRequest request) {
		return orderService.placeOrder(request);
	}

	@GetMapping("/getOrderDetails/{orderId}")
	public OrderResponse getOrderDetails(@PathVariable Long orderId) {
		return orderService.getOrderDetails(orderId);
	}

	@PostMapping("/checkout/{customerName}")
	public ResponseEntity<String> checkout(@PathVariable String customerName, @RequestBody String address) {

		List<Cart> cartItems = cartRepo.findByCustomerName(customerName);

		if (cartItems.isEmpty()) {
			return ResponseEntity.badRequest().body("Cart is empty");
		}

		for (Cart cart : cartItems) {
			orderService.placeOrderFromCart(cart, address);
		}

		cartRepo.deleteAll(cartItems);

		return ResponseEntity.ok("Order placed successfully");
	}

//Latest order
	@GetMapping("/latest/{customerName}")
	public OrderResponse getLatestOrder(@PathVariable String customerName) {
		return orderService.getLatestOrder(customerName);
	}

}