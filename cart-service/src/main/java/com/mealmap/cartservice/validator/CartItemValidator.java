package com.mealmap.cartservice.validator;

import com.mealmap.cartservice.config.CartConfig;
import com.mealmap.cartservice.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.cartservice.core.message.ApplicationMessages.CART_ITEM_IS_FULL;

@Component
@RequiredArgsConstructor
public class CartItemValidator {
    private final CartConfig cartConfig;

    public void validateQuantityOfProducts(int quantity) {
        if (quantity > cartConfig.getMaxProductQuantityInCart()) {
            throw new ForbiddenException(CART_ITEM_IS_FULL);
        }
    }
}
