package com.mealmap.userservice.core.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import static com.mealmap.userservice.core.constant.ValidationRegex.PHONE_BELARUS_FORMAT;

@Value
@Builder
public class UserUpdatingDto {
    @NotEmpty
    @Size(max = 13)
    @Pattern(regexp = PHONE_BELARUS_FORMAT)
    String phoneNumber;

    @Email
    @NotEmpty
    @Size(max = 50)
    String email;

    @NotEmpty
    @Size(max = 50)
    String firstName;

    @NotEmpty
    @Size(max = 50)
    String lastName;

    @NotNull
    Integer organizationId;
}
