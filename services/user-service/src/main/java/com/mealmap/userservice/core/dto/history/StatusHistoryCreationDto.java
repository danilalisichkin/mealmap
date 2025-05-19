package com.mealmap.userservice.core.dto.history;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация для изменения статуса пользователя")
public class StatusHistoryCreationDto {
    @Size(min = 1, max = 50)
    private String reason;
}
