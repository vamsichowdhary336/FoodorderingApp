package com.batch56.restaurant_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch56.restaurant_service.model.Users;
import com.batch56.restaurant_service.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public Users register(Users user) {
		return userRepo.save(user);
	}

	public Users login(String username, String password) {
		return userRepo.findByUsername(username).filter(u -> u.getPassword().equals(password))
				.orElseThrow(() -> new RuntimeException("Invalid username or password"));
	}

}
