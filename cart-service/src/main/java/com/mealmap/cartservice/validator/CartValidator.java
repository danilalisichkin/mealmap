package com.mealmap.cartservice.validator;

import com.mealmap.cartservice.config.CartConfig;
import com.mealmap.cartservice.entity.Cart;
import com.mealmap.cartservice.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.cartservice.core.message.ApplicationMessages.CART_IS_FULL;

@Component
@RequiredArgsConstructor
public class CartValidator {
    private final CartConfig cartConfig;

    public void validateQuantityOfItems(Cart cart) {
        if (cart.getItems().size() == cartConfig.getMaxItemQuantityInCart()) {
            throw new ForbiddenException(CART_IS_FULL);
        }
    }
}
