package com.mealmap.orderservice.validator;

import com.mealmap.orderservice.core.dto.order.OrderItemCreationDto;
import com.mealmap.orderservice.exception.ForbiddenException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mealmap.orderservice.core.message.ApplicationMessages.PRODUCTS_IN_ITEMS_REPEAT;

@Component
public class OrderItemValidator {
    public void validateUniquenessOfItems(List<OrderItemCreationDto> items) {
        long countOfUniqueItems = items.stream().map(OrderItemCreationDto::getProductId).distinct().count();

        if (countOfUniqueItems != items.size()) {
            throw new ForbiddenException(PRODUCTS_IN_ITEMS_REPEAT);
        }
    }
}
