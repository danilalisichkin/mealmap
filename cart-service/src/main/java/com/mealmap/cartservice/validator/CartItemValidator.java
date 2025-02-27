package com.mealmap.cartservice.validator;

import com.mealmap.cartservice.exception.ForbiddenException;
import org.springframework.stereotype.Component;

import static com.mealmap.cartservice.core.constant.CartLimits.MAX_PRODUCTS_PER_ITEM;
import static com.mealmap.cartservice.core.message.ApplicationMessages.CART_ITEM_IS_FULL;

@Component
public class CartItemValidator {
    public void validateQuantityOfProducts(int quantity) {
        if (quantity > MAX_PRODUCTS_PER_ITEM) {
            throw new ForbiddenException(CART_ITEM_IS_FULL);
        }
    }
}
