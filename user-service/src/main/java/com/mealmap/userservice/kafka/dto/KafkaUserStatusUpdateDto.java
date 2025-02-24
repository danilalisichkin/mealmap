package com.mealmap.userservice.kafka.dto;

import com.mealmap.userservice.entity.value.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class KafkaUserStatusUpdateDto {
    private UUID id;

    private UserStatus status;
}
