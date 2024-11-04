package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.ProductDto;

@FeignClient(name = "product-service", url = "http://localhost:8080/api/product")
public interface ProductClient {

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable("id") Long productId);
}
