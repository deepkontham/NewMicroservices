package com.example.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
	
	private Long orderId;

	private Long userId;

	private String orderStatus;

	private String paymentStatus;

	private double orderAmount;

	private String billAddress;

	private String billingPhone;

	private LocalDate orderDate;

	private LocalDate deliveredDate;
	
    private List<OrderItemDto> orderItems;

	

}
