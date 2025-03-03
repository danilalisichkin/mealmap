package com.mealmap.orderservice.client.dto.promo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromoCodeDto {
    private String value;

    private Long limit;

    private Integer discountPercentage;

    private LocalDate startDate;

    private LocalDate endDate;
}
