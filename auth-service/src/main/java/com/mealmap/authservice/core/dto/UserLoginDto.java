package com.mealmap.authservice.core.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserLoginDto {
    String identifier;

    String password;
}
