package com.microservices.cart.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private String cartId;
    private LocalDate createdDate;
    private String userId;
    private Double totalAmount;


    //mapping cart-items
    private List<CartItemDto> items=new ArrayList<>();
}