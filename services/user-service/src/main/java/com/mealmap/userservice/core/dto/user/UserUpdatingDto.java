package com.mealmap.userservice.core.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "Информация для обновления пользователя")
public class UserUpdatingDto {
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
