package com.mealmap.userservice.kafka.dto;

import com.mealmap.userservice.entity.value.UserStatus;
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

    private String role;

    private LocalDate createdAt;
}
