package com.mealmap.promoservice.core.dto.promo.stat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoStatCreationDto {
    @NotEmpty
    @Size(min = 2, max = 20)
    private String promoCode;

    @NotEmpty
    @UUID
    private String userId;
}
