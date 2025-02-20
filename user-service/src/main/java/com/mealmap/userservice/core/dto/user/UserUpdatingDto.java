package com.mealmap.userservice.core.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserUpdatingDto {
    String phoneNumber;

    String email;

    String firstName;

    String lastName;

    Integer organizationId;
}
