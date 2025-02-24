package com.mealmap.authservice.kafka.dto;

import com.mealmap.authservice.core.dto.UserStatus;
import com.mealmap.authservice.core.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class KafkaUserCreationDto {
    private UUID id;

    private String phoneNumber;

    private String email;

    private String firstName;

    private String lastName;

    private Integer organizationId;

    private UserStatus status;

    private UserRole role;

    private LocalDate createdAt;
}
