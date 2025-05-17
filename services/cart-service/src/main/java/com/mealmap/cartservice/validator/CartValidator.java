package com.mealmap.cartservice.validator;

import com.mealmap.cartservice.entity.Cart;
import com.mealmap.cartservice.repository.CartRepository;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.mealmap.cartservice.core.constant.CartLimits.MAX_ITEMS_PER_CART;
import static com.mealmap.cartservice.core.message.ApplicationMessages.CART_IS_FULL;
import static com.mealmap.cartservice.core.message.ApplicationMessages.USER_CART_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class CartValidator {
    private final CartRepository cartRepository;

    public void validateQuantityOfItems(Cart cart) {
        if (cart.getItems().size() >= MAX_ITEMS_PER_CART) {
            throw new ForbiddenException(CART_IS_FULL);
        }
    }

    public void validateUserIdUniqueness(UUID id) {
        if (cartRepository.existsByUserId(id)) {
            throw new ConflictException(USER_CART_ALREADY_EXISTS.formatted(id.toString()));
        }
    }
}
