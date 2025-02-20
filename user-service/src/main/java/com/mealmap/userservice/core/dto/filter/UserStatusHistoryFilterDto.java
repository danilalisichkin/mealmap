package com.mealmap.userservice.core.dto.filter;

import com.mealmap.userservice.entity.enums.StatusEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusHistoryFilterDto {
    private StatusEvent statusEvent;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
