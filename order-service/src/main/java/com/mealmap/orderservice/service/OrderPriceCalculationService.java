package com.mealmap.orderservice.service;

import com.mealmap.orderservice.core.dto.price.PriceCalculationRequest;
import com.mealmap.orderservice.core.dto.price.PriceRecalculationRequest;

public interface OrderPriceCalculationService {
    long calculateBaseOrderPrice(PriceCalculationRequest calculationRequest);

    long recalculatePriceWithDiscount(PriceRecalculationRequest recalculationRequest);
}
