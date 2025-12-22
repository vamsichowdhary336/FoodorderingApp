package com.batch56.restaurant_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.batch56.restaurant_service.model.FoodItems;

@Repository
public interface FoodItemsRepo extends JpaRepository<FoodItems, Long> {

	@Query("""
			    SELECT f
			    FROM FoodItems f
			    WHERE LOWER(f.restaurant.name) = LOWER(:restaurantName)
			""")
	List<FoodItems> findByRestaurantName(@Param("restaurantName") String restaurantName);

	List<FoodItems> findByNameIgnoreCase(String name);

}
