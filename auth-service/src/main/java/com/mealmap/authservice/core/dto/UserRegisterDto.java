package com.mealmap.authservice.core.dto;

import com.mealmap.authservice.core.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import static com.mealmap.authservice.core.constant.ValidationRegex.PHONE_BELARUS_FORMAT;

@Value
@Builder
public class UserRegisterDto {
    @NotEmpty
    @Size(max = 13)
    @Pattern(regexp = PHONE_BELARUS_FORMAT)
    String phoneNumber;

    @Email
    @NotEmpty
    @Size(max = 50)
    String email;

    @NotEmpty
    @Size(min = 4, max = 50)
    String password;

    @NotEmpty
    @Size(max = 50)
    String firstName;

    @NotEmpty
    @Size(max = 50)
    String lastName;

    @NotNull
    Integer organizationId;

    @NotNull
    UserRole role;
}
