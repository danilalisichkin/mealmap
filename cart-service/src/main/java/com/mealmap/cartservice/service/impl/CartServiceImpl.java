package com.mealmap.cartservice.service.impl;

import com.mealmap.cartservice.core.dto.cart.CartDto;
import com.mealmap.cartservice.core.dto.cart.CartItemAddingDto;
import com.mealmap.cartservice.core.dto.cart.CartItemDto;
import com.mealmap.cartservice.core.mapper.CartItemMapper;
import com.mealmap.cartservice.core.mapper.CartMapper;
import com.mealmap.cartservice.entity.Cart;
import com.mealmap.cartservice.entity.CartItem;
import com.mealmap.cartservice.exception.ResourceNotFoundException;
import com.mealmap.cartservice.repository.CartItemRepository;
import com.mealmap.cartservice.repository.CartRepository;
import com.mealmap.cartservice.service.CartService;
import com.mealmap.cartservice.validator.CartItemValidator;
import com.mealmap.cartservice.validator.CartValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.mealmap.cartservice.core.message.ApplicationMessages.CART_ITEM_NOT_FOUND;
import static com.mealmap.cartservice.core.message.ApplicationMessages.CART_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartValidator cartValidator;

    private final CartItemValidator cartItemValidator;

    private final CartMapper cartMapper;

    private final CartItemMapper cartItemMapper;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    @Override
    public CartDto getCart(UUID id) {
        Cart cart = getCartEntity(id);

        return cartMapper.entityToDto(cart);
    }

    @Override
    @Transactional
    public CartDto addItemToCart(UUID id, CartItemAddingDto itemDto) {
        Cart cart = getCartEntity(id);

        CartItem existingItem = findExistingCartItem(cart, itemDto.getProductId());
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + itemDto.getQuantity();
            changeCartItemQuantity(existingItem, newQuantity);
        } else {
            addNewItemToCart(cart, itemDto);
        }
        setCartUpdateTime(cart);

        return cartMapper.entityToDto(
                cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartItemDto changeCartItemQuantity(UUID id, Long itemId, Integer quantity) {
        CartItem cartItemToUpdate = getCartItemEntity(itemId, id);

        changeCartItemQuantity(cartItemToUpdate, quantity);
        setCartUpdateTime(cartItemToUpdate.getCart());

        return cartItemMapper.entityToDto(
                cartItemRepository.save(cartItemToUpdate));
    }

    @Override
    @Transactional
    public void deleteItemFromCart(UUID id, Long itemId) {
        CartItem cartItemToDelete = getCartItemEntity(itemId, id);

        cartItemToDelete.getCart().getItems().remove(cartItemToDelete);
        setCartUpdateTime(cartItemToDelete.getCart());

        cartItemRepository.delete(cartItemToDelete);
    }

    @Override
    @Transactional
    public void clearCart(UUID id) {
        Cart cartToClear = getCartEntity(id);

        cartRepository.delete(cartToClear);

        cartRepository.save(
                createNewEmptyCart(cartToClear));
    }

    private Cart getCartEntity(UUID id) {
        return cartRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        CART_NOT_FOUND.formatted(id.toString())));
    }

    private CartItem getCartItemEntity(Long cartItemId, UUID cartId) {
        return cartItemRepository
                .findByIdAndCartId(cartItemId, cartId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        CART_ITEM_NOT_FOUND.formatted(cartItemId, cartId.toString())));
    }

    private CartItem findExistingCartItem(Cart cart, Long productId) {
        return cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    private void changeCartItemQuantity(CartItem cartItem, Integer quantity) {
        cartItemValidator.validateQuantityOfProducts(quantity);
        cartItem.setQuantity(quantity);
    }

    private void addNewItemToCart(Cart cart, CartItemAddingDto itemDto) {
        cartValidator.validateQuantityOfItems(cart);
        CartItem newItem = cartItemMapper.dtoToEntity(itemDto);
        newItem.setCart(cart);
        cart.getItems().add(newItem);
    }

    private void setCartUpdateTime(Cart cart) {
        cart.setUpdatedAt(LocalDateTime.now());
    }

    private Cart createNewEmptyCart(Cart cart) {
        return Cart.builder()
                .id(cart.getId())
                .build();
    }
}
