package com.mealmap.orderservice.client.dto.promo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromoCodeDto {
    private String value;

    private Integer discountPercentage;
}
