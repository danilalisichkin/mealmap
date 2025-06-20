package com.mealmap.cartservice.controller.api;

import com.mealmap.cartservice.controller.doc.UserCartControllerDoc;
import com.mealmap.cartservice.core.dto.cart.CartDto;
import com.mealmap.cartservice.core.dto.cart.CartItemAddingDto;
import com.mealmap.cartservice.core.dto.cart.CartItemDto;
import com.mealmap.cartservice.service.UserCartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/v1/users")
public class UserCartController implements UserCartControllerDoc {
    private final UserCartService userCartService;

    @Override
    @GetMapping("/{userId}/cart")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID userId) {
        CartDto cart = userCartService.getCart(userId);

        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @Override
    @PostMapping("/{userId}/cart/items")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<CartDto> addItemToCart(
            @PathVariable UUID userId,
            @RequestBody @Valid CartItemAddingDto itemDto) {

        CartDto cart = userCartService.addItemToCart(userId, itemDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @Override
    @PatchMapping("/{userId}/cart/items/{itemId}/quantity")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<CartItemDto> changeCartItemQuantity(
            @PathVariable UUID userId,
            @PathVariable Long itemId,
            @RequestBody @NotNull @Positive @Max(20) Integer quantity) {

        CartItemDto cartItem = userCartService.changeCartItemQuantity(userId, itemId, quantity);

        return ResponseEntity.status(HttpStatus.OK).body(cartItem);
    }

    @Override
    @DeleteMapping("/{userId}/cart/items/{itemId}")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<Void> deleteItemFromCart(
            @PathVariable UUID userId,
            @PathVariable Long itemId) {

        userCartService.deleteItemFromCart(userId, itemId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @DeleteMapping("/{userId}/cart")
    @PreAuthorize("hasUserId(#userId) and hasRole('CUSTOMER')")
    public ResponseEntity<Void> clearCart(@PathVariable UUID userId) {
        userCartService.clearCart(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
