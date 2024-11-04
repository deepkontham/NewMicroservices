package com.microservices.cart.controllers;


import com.microservices.cart.dtos.CartDto;
import com.microservices.cart.dtos.CartItemDto;
import com.microservices.cart.repositories.CartItemRepository;
import com.microservices.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;



    //add items to cart
    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable  String userId, @RequestBody CartItemDto request){
        CartDto cartDto = cartService.addItemToCart(userId, request);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

//    @DeleteMapping("/{userId}/items/{itemId}")
//    public ResponseEntity<ApiResponseMessage> removeItemFromCart(@PathVariable String userId, @PathVariable String itemId){
//        cartService.removeItemFromCart(userId,itemId);
//        ApiResponseMessage response = ApiResponseMessage.builder()
//                .message("Item is removed !!")
//                .success(true)
//                .status(HttpStatus.OK)
//                .build();
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    //clear cart
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<ApiResponseMessage> clearCart(@PathVariable String userId){
//        cartService.clearCart(userId);
//        ApiResponseMessage response = ApiResponseMessage.builder()
//                .message("Not cart is blank !!")
//                .success(true)
//                .status(HttpStatus.OK)
//                .build();
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId){
        CartDto cartDto = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCart(){
        List<CartDto> carts=cartService.getAllCart();
        return ResponseEntity.ok(carts);
    }


}

