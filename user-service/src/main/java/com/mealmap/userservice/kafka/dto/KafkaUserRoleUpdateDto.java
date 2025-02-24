package com.mealmap.userservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class KafkaUserRoleUpdateDto {
    private UUID id;

    private String oldRole;

    private String newRole;
}
