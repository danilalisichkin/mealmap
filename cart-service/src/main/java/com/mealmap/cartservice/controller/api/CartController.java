package com.mealmap.cartservice.controller.api;

import com.mealmap.cartservice.core.dto.cart.CartDto;
import com.mealmap.cartservice.core.dto.cart.CartItemAddingDto;
import com.mealmap.cartservice.core.dto.cart.CartItemDto;
import com.mealmap.cartservice.service.CartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID id) {
        CartDto cart = cartService.getCart(id);

        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<CartDto> addItemToCart(
            @PathVariable UUID id,
            @RequestBody @Valid CartItemAddingDto itemDto) {

        CartDto cart = cartService.addItemToCart(id, itemDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @PatchMapping("/{id}/items/{itemId}/quantity")
    public ResponseEntity<CartItemDto> changeCartItemQuantity(
            @PathVariable UUID id,
            @PathVariable Long itemId,
            @RequestBody @NotNull @Positive @Max(20) Integer quantity) {

        CartItemDto cartItem = cartService.changeCartItemQuantity(id, itemId, quantity);

        return ResponseEntity.status(HttpStatus.OK).body(cartItem);
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> deleteItemFromCart(
            @PathVariable UUID id,
            @PathVariable Long itemId) {

        cartService.deleteItemFromCart(id, itemId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> clearCart(@PathVariable UUID id) {
        cartService.clearCart(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
