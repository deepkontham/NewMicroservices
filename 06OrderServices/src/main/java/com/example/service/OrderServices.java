package com.example.service;

import java.util.List;

import com.example.dto.OrderDto;
import com.example.entity.Order;

public interface OrderServices {
	
	public Order addOrder(OrderDto order);
	
	public List<OrderDto> orders();
	
	public void  delete(Long orderId);
	
	public OrderDto getOrderById(Long orderId);
	
	
	

	
}
