package com.mealmap.authservice.core.dto;

import com.mealmap.authservice.core.enums.UserRole;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class UserDto {
    UUID id;

    String phoneNumber;

    String email;

    String firstName;

    String lastName;

    Integer organizationId;

    Boolean isActive;

    Boolean isBlocked;

    Boolean isTemporaryBlocked;

    UserRole role;

    LocalDate createdAt;
}
