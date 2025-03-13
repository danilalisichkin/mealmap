package com.mealmap.organizationservice.core.dto.filter;

import com.mealmap.organizationservice.entity.enums.OrganizationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationFilterDto {
    @Positive
    private Integer upn;

    @Size(min = 2, max = 100)
    private String name;

    @Size(min = 2, max = 150)
    private String legalAddress;

    @Size(min = 3, max = 20)
    private String phoneNumber;

    @Email
    @Size(min = 2, max = 50)
    private String email;

    private OrganizationType type;
}
