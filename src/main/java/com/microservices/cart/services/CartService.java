package com.microservices.cart.services;

import com.microservices.cart.dtos.CartDto;
import com.microservices.cart.dtos.CartItemDto;

import java.util.List;

public interface CartService {

    //add item to cart:
    //case:1 cart for the user is not available: we will create the cart and add the item
    //cart available:add the item to the cart

    CartDto addItemToCart(String userId, CartItemDto request);

    //Remove item from cart
//    void removeItemFromCart(String userId,String cartItemId);
//
//    //remove all item from the cart
//    void clearCart(String userId);

    CartDto getCartByUser(String userId);

    List<CartDto> getAllCart();

}
