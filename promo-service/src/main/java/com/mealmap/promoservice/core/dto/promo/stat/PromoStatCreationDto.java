package com.mealmap.promoservice.core.dto.promo.stat;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PromoStatCreationDto {
    String promoCode;

    String userId;
}
