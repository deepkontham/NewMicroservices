package com.microservices.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.cart.dtos.ProductDto;


@FeignClient(name = "product-service", url = "http://localhost:8080/api/product")
public interface ProductSeviceClient {
	
	 @GetMapping("/{id}")
	    ProductDto getProductById(@PathVariable("id") Long productId);

}
