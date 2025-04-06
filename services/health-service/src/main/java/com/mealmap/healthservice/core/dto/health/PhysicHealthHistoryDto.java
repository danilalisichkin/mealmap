package com.mealmap.healthservice.core.dto.health;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicHealthHistoryDto {
    private Long id;

    private Integer weight;

    private LocalDate createdAt;
}
