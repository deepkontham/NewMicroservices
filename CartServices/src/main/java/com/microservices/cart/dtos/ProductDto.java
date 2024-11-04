package com.microservices.cart.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDto {


	
	private Long productId;
	
	private String productTitle;

	private double productPrice;

	private String productDescription;
}