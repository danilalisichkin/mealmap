package com.mealmap.organizationservice.core.dto.organization;

import com.mealmap.organizationservice.entity.enums.OrganizationType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationCreationDto {
    @Positive
    @NotNull
    private Integer upn;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

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

    @NotNull
    private OrganizationType type;
}
