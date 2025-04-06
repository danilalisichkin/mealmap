package com.mealmap.cartservice.service;

import com.mealmap.cartservice.core.dto.cart.CartDto;
import com.mealmap.cartservice.core.dto.cart.CartItemAddingDto;
import com.mealmap.cartservice.core.dto.cart.CartItemDto;

import java.util.UUID;

public interface UserCartService {
    CartDto getCart(UUID userId);

    CartDto addItemToCart(UUID userId, CartItemAddingDto itemDto);

    CartItemDto changeCartItemQuantity(UUID userId, Long itemId, Integer quantity);

    void deleteItemFromCart(UUID userId, Long itemId);

    void clearCart(UUID userId);
}
