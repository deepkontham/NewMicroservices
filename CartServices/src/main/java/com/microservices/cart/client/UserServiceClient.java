package com.microservices.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.cart.dtos.UserDto;

@FeignClient(name = "User-service", url = "http://localhost:8086/users")
public interface UserServiceClient {
	
	 @GetMapping("/{userId}")
	    UserDto getUserById(@PathVariable("userId") String userId);
	}
	


