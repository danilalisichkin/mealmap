package com.mealmap.orderservice.service.impl;

import com.mealmap.orderservice.client.dto.promo.PromoCodeDto;
import com.mealmap.orderservice.core.dto.price.PriceCalculationRequest;
import com.mealmap.orderservice.core.dto.price.PriceRecalculationRequest;
import com.mealmap.orderservice.service.OrderPriceCalculationService;
import com.mealmap.orderservice.service.PromoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPriceCalculationServiceImpl implements OrderPriceCalculationService {
    private final PromoCodeService promoCodeService;

    @Override
    public long calculateBaseOrderPrice(PriceCalculationRequest calculationRequest) {
        return calculationRequest.getOrderItems().stream()
                .mapToLong(item -> item.getPriceWhenOrdered() * item.getQuantity())
                .sum();
    }

    @Override
    public long recalculatePriceWithDiscount(PriceRecalculationRequest recalculationRequest) {
        PromoCodeDto promoCode = promoCodeService.getPromoCode(recalculationRequest.getPromoCode());
        promoCodeService.createPromoStat(recalculationRequest.getUserId(), promoCode.getValue());

        Long originalBasePrice = recalculationRequest.getBasePrice();
        Integer discountPercentage = promoCode.getDiscountPercentage();
        Double discountAmount = originalBasePrice * discountPercentage / 100.0;

        return originalBasePrice - discountAmount.longValue();
    }
}
