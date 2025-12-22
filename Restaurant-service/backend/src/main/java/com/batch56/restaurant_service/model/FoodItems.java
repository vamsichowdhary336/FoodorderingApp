package com.batch56.restaurant_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fooditems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;

	private String imageUrl;

	@ManyToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;
}
