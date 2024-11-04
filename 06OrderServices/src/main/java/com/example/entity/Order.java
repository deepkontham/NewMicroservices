package com.example.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders") 
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	private String orderStatus;

	private Long userId;
	
	private String paymentStatus;

	private Double orderAmount;

	private String billAddress;

	private String billingPhone;

	private LocalDate orderDate;

	private LocalDate deliveredDate;

	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
	    private List<OrderItem> orderItems = new ArrayList<>();
   

	 
	 public void addOrderItem(OrderItem item) {
	        this.orderItems.add(item);
	        item.setOrder(this);
	    }
}
