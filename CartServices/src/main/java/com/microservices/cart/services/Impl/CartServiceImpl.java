package com.microservices.cart.services.Impl;

import com.microservices.cart.client.ProductSeviceClient;
import com.microservices.cart.client.UserServiceClient;
import com.microservices.cart.dtos.CartDto;
import com.microservices.cart.dtos.CartItemDto;
import com.microservices.cart.dtos.ProductDto;
import com.microservices.cart.dtos.UserDto;
import com.microservices.cart.entities.Cart;
import com.microservices.cart.entities.CartItem;
import com.microservices.cart.exceptions.ResourceNotFoundException;
import com.microservices.cart.repositories.CartRepository;
import com.microservices.cart.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
	
	
	@Autowired
    private CartRepository cartRepository;

   
    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ProductSeviceClient productServiceClient;
    
    @Autowired
    private ModelMapper mapper;

    public CartDto addItemToCart(String userId, CartItemDto request) {
        UserDto user = userServiceClient.getUserById(userId);
        ProductDto product = productServiceClient.getProductById(request.getProductId());

        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> createNewCart(userId));

        CartItem cartItem = new CartItem();
        cartItem.setProductId(product.getProductId());
        cartItem.setProductTitle(product.getProductTitle());
        cartItem.setQuantity(request.getQuantity());
        cartItem.setTotalPrice((int) (product.getProductPrice() * request.getQuantity()));
        cartItem.setCart(cart);

        cart.getItems().add(cartItem);
        cart.setTotalAmount(cart.getItems().stream().mapToDouble(CartItem::getTotalPrice).sum());

        Cart savecart = cartRepository.save(cart);
        return mapToDto(savecart);
    }

    private Cart createNewCart(String userId) {
        Cart cart = new Cart();
        cart.setCartId(UUID.randomUUID().toString());
        cart.setCreatedDate(LocalDate.now());
        cart.setTotalAmount(0.0);
        cart.setUserId(userId);
        return cart;
    }

    private CartDto mapToDto(Cart cart) {
    	
    	CartDto map = mapper.map(cart, CartDto.class);
		return map;
    }
	
	

	

	
//	private void updateCartItems(Cart cart, int quantity, ProductDto product) {
//		List<CartItem> items = cart.getItems();
//		boolean itemUpdated = false;
//
//		for (CartItem item : items) {
//			if (item.getProductId().equals(product.getProductId())) {
//				// Update existing item
//				item.setQuantity(quantity);
//				item.setTotalPrice(quantity * product.getDiscountedPrice());
//				itemUpdated = true;
//				break; // Exit loop once the item is found and updated
//			}
//		}
//
//		if (!itemUpdated) {
//			// Create new cart item if not already present
//			CartItem newCartItem = CartItem.builder().quantity(quantity)
//					.totalPrice(quantity * product.getDiscountedPrice()).cart(cart).productId(product.getProductId())
//					.build();
//			cart.getItems().add(newCartItem);
//		}
//	}

//	@Override
//	public void removeItemFromCart(String userId, String cartItemId) {
//		CartItem cartItem1 = cartItemRepository.findById(cartItemId)
//				.orElseThrow(() -> new ResourceNotFoundException("Cart item not found in db"));
//		cartItemRepository.delete(cartItem1);
//
//	}
//
//	@Override
//	public void clearCart(String userId) {
//		Cart cart = cartRepository.findByUserId(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));
//
//		cart.getItems().clear();
//		cartRepository.save(cart);
//
//	}

	@Override
	public CartDto getCartByUser(String userId) {
		Cart cart = cartRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));

		return mapper.map(cart, CartDto.class);
	}

	
	@Override
	public List<CartDto> getAllCart() {
		List<Cart> carts = cartRepository.findAll(); // Assuming you have a method in CartRepository to fetch all carts
		return carts.stream().map(cart -> mapper.map(cart, CartDto.class)).collect(Collectors.toList());
	}
}
