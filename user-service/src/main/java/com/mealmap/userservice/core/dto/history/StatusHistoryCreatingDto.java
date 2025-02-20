package com.mealmap.userservice.core.dto.history;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatusHistoryCreatingDto {
    @Size(min = 1, max = 50)
    String reason;
}
