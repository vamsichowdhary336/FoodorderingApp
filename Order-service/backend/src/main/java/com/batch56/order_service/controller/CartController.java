package com.batch56.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch56.order_service.model.Cart;
import com.batch56.order_service.repo.CartRepo;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/cart")
public class CartController {

	@Autowired
	private CartRepo cartRepo;

	@PostMapping("/add")
	public String addToCart(@RequestBody Cart cart) {
		cart.setQuantity(1);
		cartRepo.save(cart);
		return "Item Added to cart";
	}

	@GetMapping("/view/{customerName}")
	public List<Cart> viewCart(@PathVariable String customerName) {
		return cartRepo.findByCustomerName(customerName);
	}

	@DeleteMapping("/remove/{cartId}")
	public ResponseEntity<String> removeItem(@PathVariable Long cartId) {
		cartRepo.deleteById(cartId);
		return ResponseEntity.ok("Item removed from cart");
	}

	@PutMapping("/update/{cartId}/{qty}")
	public ResponseEntity<String> updateQty(@PathVariable Long cartId, @PathVariable int qty) {

		Cart cart = cartRepo.findById(cartId).orElseThrow();
		cart.setQuantity(qty);
		cartRepo.save(cart);

		return ResponseEntity.ok("Quantity updated");
	}

	@DeleteMapping("/clear/{customerName}")
	@Transactional
	public ResponseEntity<String> clearCart(@PathVariable String customerName) {
		cartRepo.deleteByCustomerName(customerName);
		return ResponseEntity.ok("Cart cleared");
	}
}
