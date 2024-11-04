package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDto;
import com.example.service.ProductServicesImpl;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductServicesImpl impl;

	@PostMapping("/save")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto) {
		return new ResponseEntity<ProductDto>(impl.saveProduct(dto), HttpStatus.CREATED);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getproductById(@PathVariable("id") Long productId) {
		return new ResponseEntity<ProductDto>(impl.getProductById(productId), HttpStatus.OK);
	}

	
	@GetMapping("/getAll")
	public List<ProductDto> getproducts() {
		List<ProductDto> products = impl.products();
		return products;
	}

}
