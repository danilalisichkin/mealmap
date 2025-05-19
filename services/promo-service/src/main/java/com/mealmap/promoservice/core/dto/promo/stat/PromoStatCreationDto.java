package com.mealmap.promoservice.core.dto.promo.stat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация для использования промокода")
public class PromoStatCreationDto {
    @NotEmpty
    @Size(min = 2, max = 20)
    private String promoCode;

    @NotEmpty
    @UUID
    private String userId;
}
