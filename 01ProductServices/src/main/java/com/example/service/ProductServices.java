package com.example.service;

import java.util.List;
import com.example.dto.ProductDto;


public interface ProductServices {
	
	public ProductDto saveProduct(ProductDto product);
	
	public List<ProductDto> products();
	
	public ProductDto getProductById(Long productId);
	

}
