package com.mealmap.orderservice.service;

import com.mealmap.orderservice.core.dto.price.PriceCalculationRequest;

public interface OrderPriceCalculationService {
    long calculateBaseOrderPrice(PriceCalculationRequest calculationRequest);
}
