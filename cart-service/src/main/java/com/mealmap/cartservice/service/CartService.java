package com.mealmap.cartservice.service;

import com.mealmap.cartservice.core.dto.cart.CartDto;
import com.mealmap.cartservice.core.dto.cart.CartItemAddingDto;
import com.mealmap.cartservice.core.dto.cart.CartItemDto;

import java.util.UUID;

public interface CartService {
    CartDto getCart(UUID id);

    CartDto addItemToCart(UUID id, CartItemAddingDto itemDto);

    CartItemDto changeCartItemQuantity(UUID id, Long itemId, Integer quantity);

    void deleteItemFromCart(UUID id, Long itemId);

    void clearCart(UUID id);
}
