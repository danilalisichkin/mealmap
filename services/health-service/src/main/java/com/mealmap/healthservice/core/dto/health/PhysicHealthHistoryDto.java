package com.mealmap.healthservice.core.dto.health;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация об изменении физического здоровья")
public class PhysicHealthHistoryDto {
    private Long id;

    private Integer weight;

    private LocalDate createdAt;
}
