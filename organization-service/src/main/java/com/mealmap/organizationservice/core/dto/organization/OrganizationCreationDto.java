package com.mealmap.organizationservice.core.dto.organization;

import com.mealmap.organizationservice.entity.enums.OrganizationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationCreationDto {
    private Integer upn;

    private String name;

    private String legalAddress;

    private String phoneNumber;

    private String email;

    private OrganizationType type;
}
