package com.mealmap.organizationservice.core.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Информация для изменения организации")
public class OrganizationUpdatingDto {
    @Positive
    @NotNull
    private Integer upn;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @Size(max = 255)
    private String imageUrl;

    @NotEmpty
    @Size(min = 2, max = 150)
    private String legalAddress;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String phoneNumber;

    @Email
    @NotEmpty
    @Size(min = 2, max = 50)
    private String email;
}
