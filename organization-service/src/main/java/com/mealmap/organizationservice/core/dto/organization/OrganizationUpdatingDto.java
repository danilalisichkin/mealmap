package com.mealmap.organizationservice.core.dto.organization;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationUpdatingDto {
    private Integer upn;

    private String name;

    private String legalAddress;

    private String phoneNumber;

    private String email;
}
