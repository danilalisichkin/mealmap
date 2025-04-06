package com.mealmap.orderservice.client.dto.promo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoStatCreationDto {
    private String promoCode;

    private String userId;
}
