package com.mealmap.userservice.kafka.dto;

import com.mealmap.userservice.entity.value.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserStatusUpdateDto {
    private UUID id;

    private UserStatus status;
}
