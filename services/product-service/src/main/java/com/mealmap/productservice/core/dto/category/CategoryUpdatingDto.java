package com.mealmap.productservice.core.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "Информация для обновления категории")
public class CategoryUpdatingDto {
    @NotEmpty
    @Size(max = 50)
    String name;

    Long parent;
}
