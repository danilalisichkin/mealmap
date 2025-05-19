package com.mealmap.productservice.core.dto.allergen;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "Информация для создания аллергена")
public class AllergenCreationDto {
    @NotEmpty
    @Size(max = 50)
    String name;
}
