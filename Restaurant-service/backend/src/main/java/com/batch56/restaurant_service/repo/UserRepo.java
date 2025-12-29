package com.batch56.restaurant_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batch56.restaurant_service.model.Users;

public interface UserRepo extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);
}
