package com.mealmap.authservice.kafka.dto;

import com.mealmap.authservice.core.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class KafkaUserStatusUpdateDto {
    private UUID id;

    private UserStatus status;
}
