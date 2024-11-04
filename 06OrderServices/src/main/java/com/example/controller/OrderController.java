package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OrderDto;
import com.example.entity.Order;
import com.example.service.OrderServiceImpl;


@RestController
@RequestMapping("/api/order")
public class OrderController{
	
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	
	@PostMapping("/save")
	public ResponseEntity<Order> createOrder(@RequestBody OrderDto dto){
	 Order addOrder = orderServiceImpl.addOrder(dto);
	return ResponseEntity.ok(addOrder);
		
	}
	
	
	@GetMapping("/getAll")
	public List<OrderDto> getAll(){
		 List<OrderDto> orders = orderServiceImpl.orders();
		return orders;
		
		
	}
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<OrderDto> getOrdersById(@PathVariable("id") Long orderId){
		return new ResponseEntity<OrderDto>(orderServiceImpl.getOrderById(orderId),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable ("id") Long orderId){
		orderServiceImpl.delete(orderId);
		return new ResponseEntity<String>("deleted Successfully",HttpStatus.OK);
	} 
	
	
	

}
