package com.mealmap.orderservice.service.impl;

import com.mealmap.orderservice.core.dto.price.PriceCalculationRequest;
import com.mealmap.orderservice.service.OrderPriceCalculationService;
import org.springframework.stereotype.Service;

@Service
public class OrderPriceCalculationServiceImpl implements OrderPriceCalculationService {
    @Override
    public long calculateBaseOrderPrice(PriceCalculationRequest calculationRequest) {
        return calculationRequest.getOrderItems().stream()
                .mapToLong(item -> item.getPriceWhenOrdered() * item.getQuantity())
                .sum();
    }
}
