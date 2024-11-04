package com.microservices.cart;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CartServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServicesApplication.class, args);
	}
	
}
