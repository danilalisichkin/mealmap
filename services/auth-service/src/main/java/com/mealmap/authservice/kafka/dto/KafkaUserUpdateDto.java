package com.mealmap.authservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class KafkaUserUpdateDto {
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private Integer organizationId;
}
