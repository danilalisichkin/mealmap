package com.mealmap.authservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "Учетные данные для входа в систему")
public class UserLoginDto {
    @NotEmpty
    @Size(min = 3, max = 100)
    String identifier;

    @NotEmpty
    @Size(min = 4, max = 50)
    String password;
}
