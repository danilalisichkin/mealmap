package com.mealmap.userservice.core.dto.user;

import com.mealmap.userservice.entity.enums.StatusEvent;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatusHistoryCreatingDto {
    StatusEvent newStatus;

    String reason;
}
