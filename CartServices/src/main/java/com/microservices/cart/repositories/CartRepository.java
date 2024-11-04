package com.microservices.cart.repositories;

import com.microservices.cart.dtos.UserDto;
import com.microservices.cart.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String> {
    Optional<Cart> findByUserId(String userId);


}
