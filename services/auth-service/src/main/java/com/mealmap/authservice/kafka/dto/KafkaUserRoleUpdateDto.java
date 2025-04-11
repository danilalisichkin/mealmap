package com.mealmap.authservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserRoleUpdateDto {
    private UUID id;

    private boolean isToAssign;

    private String newRole;
}
