package com.mealmap.userservice.core.dto.history;

import com.mealmap.userservice.entity.enums.StatusEvent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatusHistoryCreatingDto {
    @NotNull
    StatusEvent newStatus;

    @Size(min = 1, max = 50)
    String reason;
}
