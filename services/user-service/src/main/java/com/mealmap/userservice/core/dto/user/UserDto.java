package com.mealmap.userservice.core.dto.user;

import com.mealmap.userservice.entity.enums.Role;
import com.mealmap.userservice.entity.value.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;

    private String phoneNumber;

    private String email;

    private String firstName;

    private String lastName;

    private Integer organizationId;

    private UserStatus status;

    private List<Role> roles;

    private LocalDate createdAt;
}
