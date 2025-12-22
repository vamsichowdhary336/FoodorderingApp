package com.batch56.restaurant_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch56.restaurant_service.model.Restaurant;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

	Optional<Restaurant> findFirstByNameIgnoreCase(String name);
}