package com.mealmap.authservice.core.dto;

import com.mealmap.authservice.core.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;

    private String phoneNumber;

    private String email;

    private String firstName;

    private String lastName;

    private Integer organizationId;

    private UserStatus status;

    private Role role;

    private LocalDate createdAt;
}
