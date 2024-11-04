package com.microservices.cart.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {

	private int cartItemId;
	private Long productId;
	private String productTitle;
    private int quantity;
    private int totalPrice;
}