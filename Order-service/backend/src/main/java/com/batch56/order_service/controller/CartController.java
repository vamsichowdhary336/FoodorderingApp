package com.batch56.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch56.order_service.model.Cart;
import com.batch56.order_service.repo.CartRepo;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

	@Autowired
	private CartRepo cartRepo;

	@PostMapping("/add")
	public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
		cartRepo.save(cart);
		return ResponseEntity.ok("Item added to cart");
	}

	@GetMapping("/view/{customerName}")
	public List<Cart> viewCart(@PathVariable String customerName) {
		return cartRepo.findByCustomerName(customerName);
	}

}
