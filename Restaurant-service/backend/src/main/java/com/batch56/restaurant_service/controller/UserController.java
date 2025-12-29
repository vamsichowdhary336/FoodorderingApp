package com.batch56.restaurant_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batch56.restaurant_service.Response.LoginResponse;
import com.batch56.restaurant_service.model.Users;
import com.batch56.restaurant_service.request.LoginRequest;
import com.batch56.restaurant_service.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	// Register
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users user) {

		if (user.getName() == null || user.getUsername() == null || user.getEmail() == null || user.getMobile() == null
				|| user.getPassword() == null || user.getAddress() == null || user.getRole() == null) {

			return ResponseEntity.badRequest().body("All fields are required");
		}

		return ResponseEntity.ok(userService.register(user));
	}

	// Login
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody LoginRequest request) {
//		userService.login(request.getUsername(), request.getPassword());
//		return ResponseEntity.ok("User logged in successfully");
//	}
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		Users user = userService.login(request.getUsername(), request.getPassword());
		return ResponseEntity.ok(new LoginResponse("Login successful", user.getRole().name()));
	}

}
