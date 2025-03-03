package com.mealmap.orderservice.service.impl;

import com.mealmap.orderservice.client.PromoCodeApiClient;
import com.mealmap.orderservice.client.PromoStatApiClient;
import com.mealmap.orderservice.client.dto.promo.PromoCodeDto;
import com.mealmap.orderservice.client.dto.promo.PromoStatCreationDto;
import com.mealmap.orderservice.service.PromoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromoCodeServiceImpl implements PromoCodeService {
    private final PromoStatApiClient promoStatApiClient;

    private final PromoCodeApiClient promoCodeApiClient;

    @Override
    public PromoCodeDto getPromoCode(String promoCode) {
        return promoCodeApiClient.getPromoCode(promoCode);
    }

    @Override
    public void createPromoStat(String userId, String promoCode) {
        PromoStatCreationDto promoStatAddingDto = new PromoStatCreationDto(promoCode, userId);
        promoStatApiClient.createPromoStat(promoStatAddingDto);
    }
}
