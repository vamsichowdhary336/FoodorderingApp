package com.batch56.restaurant_service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
	private String message;
	private String role;
}
