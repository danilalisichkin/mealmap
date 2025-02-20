package com.mealmap.userservice.core.dto.filter;

import com.mealmap.userservice.entity.enums.StatusEvent;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.mealmap.userservice.validator.RangeValidator.isValidRange;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusHistoryFilterDto {
    private StatusEvent statusEvent;

    @PastOrPresent
    private LocalDateTime startTime;

    @PastOrPresent
    private LocalDateTime endTime;

    @AssertTrue(message = "startTime must be before endTime")
    private boolean isValidTimeRange() {
        return isValidRange(startTime, endTime);
    }
}
