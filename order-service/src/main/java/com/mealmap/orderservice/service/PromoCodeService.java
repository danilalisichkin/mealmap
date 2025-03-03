package com.mealmap.orderservice.service;

import com.mealmap.orderservice.client.dto.promo.PromoCodeDto;

public interface PromoCodeService {
    PromoCodeDto getPromoCode(String promoCode);

    void createPromoStat(String userId, String promoCode);
}
