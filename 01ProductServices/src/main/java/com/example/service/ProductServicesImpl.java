package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dto.ProductDto;
import com.example.entity.Product;
import com.example.repository.ProductRepository;


@Service
public class ProductServicesImpl implements ProductServices {
	 
	@Autowired
	private ProductRepository productRepository;
	
	
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		
		
		 Product product = new Product();
	        product.setProductId(productDto.getProductId());
	        product.setProductTitle(productDto.getProductTitle());
	        product.setProductDescription(productDto.getProductDescription());
	        product.setProductPrice(productDto.getProductPrice());
	        
	        product.setAddedDate(LocalDate.now());
	        Product savedProduct = productRepository.save(product);
	        
	        return maptoDto(savedProduct);

	
	}

	
	
	private ProductDto maptoDto(Product product) {
		ProductDto map = mapper.map(product, ProductDto.class);
		return map;
		
	}
	
	private Product maptoEntity(ProductDto dto) {
		Product product = mapper.map(dto, Product.class);
		return product;
	}
	
	
	@Override
	public ProductDto getProductById(Long productId) {
		 Product product = productRepository.findById(productId).get();
		 ProductDto maptoDto = maptoDto(product);
		return maptoDto;
	}


	@Override
	public List<ProductDto> products() {
		List<Product> products = productRepository.findAll();
		return  products.stream().map(product ->maptoDto(product)).collect(Collectors.toList());
		
		
	}

}
