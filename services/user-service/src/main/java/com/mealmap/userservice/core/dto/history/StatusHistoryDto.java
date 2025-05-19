package com.mealmap.userservice.core.dto.history;

import com.mealmap.userservice.entity.enums.StatusEvent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация об изменении истории статуса пользователя")
public class StatusHistoryDto {
    private Long id;

    private UUID userId;

    private StatusEvent newStatus;

    private String reason;

    private UUID changedBy;

    private LocalDateTime eventAt;
}
