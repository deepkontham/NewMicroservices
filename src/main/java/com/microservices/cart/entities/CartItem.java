package com.microservices.cart.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;
    private Long productId;
    private String productTitle;
    private int quantity;
    private int totalPrice;
   
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


}
