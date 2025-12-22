package com.batch56.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch56.order_service.config.OrderResponse;
import com.batch56.order_service.model.Order;
import com.batch56.order_service.request.OrderRequest;
import com.batch56.order_service.service.OrderService;

@CrossOrigin(origins = { "*", "null" })
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/addorder")
	public Order addOrder(@RequestBody OrderRequest request) {
		return orderService.placeOrder(request);
	}

	@GetMapping("/getOrderDetails/{orderId}")
	public OrderResponse getOrderDetails(@PathVariable Long orderId) {
		return orderService.getOrderDetails(orderId);

	}
}