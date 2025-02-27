package com.mealmap.cartservice.validator;

import com.mealmap.cartservice.entity.Cart;
import com.mealmap.cartservice.exception.ForbiddenException;
import org.springframework.stereotype.Component;

import static com.mealmap.cartservice.core.constant.CartLimits.MAX_ITEMS_PER_CART;
import static com.mealmap.cartservice.core.message.ApplicationMessages.CART_IS_FULL;

@Component
public class CartValidator {
    public void validateQuantityOfItems(Cart cart) {
        if (cart.getItems().size() == MAX_ITEMS_PER_CART) {
            throw new ForbiddenException(CART_IS_FULL);
        }
    }
}
