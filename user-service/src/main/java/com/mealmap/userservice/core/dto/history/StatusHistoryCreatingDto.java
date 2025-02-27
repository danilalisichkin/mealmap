package com.mealmap.userservice.core.dto.history;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistoryCreatingDto {
    @Size(min = 1, max = 50)
    private String reason;
}
