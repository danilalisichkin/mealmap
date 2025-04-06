package com.mealmap.userservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserUpdateDto {
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private Integer organizationId;
}
