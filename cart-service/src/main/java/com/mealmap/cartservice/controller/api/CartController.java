package com.mealmap.cartservice.controller.api;

import com.mealmap.cartservice.core.dto.cart.CartDto;
import com.mealmap.cartservice.core.dto.cart.CartItemAddingDto;
import com.mealmap.cartservice.core.dto.cart.CartItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<List<CartItemDto>> addItemToCart(
            @PathVariable UUID id,
            @RequestBody CartItemAddingDto itemDto) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}/items/{itemId}/quantity")
    public ResponseEntity<CartItemDto> changeCartItemQuantity(
            @PathVariable UUID id,
            @PathVariable Long itemId,
            @RequestBody Integer quantity) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> deleteItemFromCart(
            @PathVariable UUID id,
            @PathVariable Long itemId) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> clearCart(@PathVariable UUID id) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
