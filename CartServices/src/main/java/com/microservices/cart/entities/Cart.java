package com.microservices.cart.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private String cartId;
    private LocalDate createdDate;
    private String userId;
    private Double totalAmount;

    //mapping cart-items
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> items=new ArrayList<>();
    
    
    public void addItem(CartItem item) {
    	this.items.add(item);
        item.setCart(this);
    }
}
