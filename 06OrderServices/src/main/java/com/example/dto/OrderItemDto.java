package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemDto {
	
	private Long productId; 
	private String product;
    private Integer quantity;
    private Double totalPrice;
    
    

}
