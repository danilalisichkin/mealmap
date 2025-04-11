package com.mealmap.authservice.kafka.dto;

import com.mealmap.authservice.core.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaUserCreationDto {
    private UUID id;

    private String phoneNumber;

    private String email;

    private String firstName;

    private String lastName;

    private Integer organizationId;

    private UserStatus status;

    private String role;

    private LocalDate createdAt;
}
