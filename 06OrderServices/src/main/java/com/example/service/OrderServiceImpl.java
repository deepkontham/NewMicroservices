package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.ProductClient;
import com.example.dto.OrderDto;
import com.example.dto.OrderItemDto;
import com.example.dto.ProductDto;
import com.example.entity.Order;
import com.example.entity.OrderItem;
import com.example.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderServices {

	@Autowired
	private OrderRepository orderRepository;

	
//	@Autowired
//	private OrderItemRepository itemRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProductClient productClient;

	@Override
	public Order addOrder(OrderDto dto) {
		Order order = new Order();
		order.setUserId(dto.getUserId());
		order.setOrderStatus(dto.getOrderStatus());
		order.setPaymentStatus(dto.getPaymentStatus());
		order.setBillAddress(dto.getBillAddress());
		order.setBillingPhone(dto.getBillingPhone());
		order.setOrderDate(LocalDate.now());
		order.setDeliveredDate(LocalDate.now().plusDays(5));

		double totalAmount = 0.0;

		for (OrderItemDto itemRequest : dto.getOrderItems()) {
			ProductDto product = productClient.getProductById(itemRequest.getProductId());
			if (product != null) {
				OrderItem orderItem = new OrderItem();
				orderItem.setProductId(itemRequest.getProductId());
				orderItem.setQuantity(itemRequest.getQuantity());
				orderItem.setTotalPrice(product.getProductPrice() * itemRequest.getQuantity());
				orderItem.setProduct(product.getProductTitle());

				order.addOrderItem(orderItem);

				totalAmount += orderItem.getTotalPrice();
			} else {
				throw new RuntimeException("Product not found with id: " + itemRequest.getProductId());
			}
		}

		order.setOrderAmount(totalAmount);
		return orderRepository.save(order);
	}

	
	
	private OrderItem mapToEntity(OrderItemDto dto) {
		OrderItem orderItemDto = mapper.map(dto, OrderItem.class);
		return orderItemDto;
	}

	private OrderItemDto mapToDto(OrderItem item) {
		OrderItemDto map = mapper.map(item, OrderItemDto.class);
		return map;
	}

	@Override
	public List<OrderDto> orders() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream().map(order -> mapToDto(order)).collect(Collectors.toList());

	}

	@Override
	public void delete(Long orderId) {
		orderRepository.deleteById(orderId);

	}

	@Override
	public OrderDto getOrderById(Long orderId) {
		Order findById = orderRepository.findById(orderId).get();
		return mapToDto(findById);
	}

	private OrderDto mapToDto(Order order) {
		OrderDto map1 = mapper.map(order, OrderDto.class);
		return map1;

	}

}
