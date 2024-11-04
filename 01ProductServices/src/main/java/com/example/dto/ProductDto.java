


package com.example.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDto {
	
	private Long productId;
	
	private String productTitle;

	private Double productPrice;

	private String productDescription;


}
