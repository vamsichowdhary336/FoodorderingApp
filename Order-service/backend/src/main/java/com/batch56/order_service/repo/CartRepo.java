package com.batch56.order_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch56.order_service.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
	List<Cart> findByCustomerName(String customerName);

}
