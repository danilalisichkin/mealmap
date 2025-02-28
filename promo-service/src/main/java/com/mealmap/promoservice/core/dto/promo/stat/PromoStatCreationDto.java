package com.mealmap.promoservice.core.dto.promo.stat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.UUID;

@Value
@AllArgsConstructor
public class PromoStatCreationDto {
    @NotEmpty
    @Size(min = 2, max = 20)
    String promoCode;

    @NotEmpty
    @UUID
    String userId;
}
