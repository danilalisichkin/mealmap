package com.mealmap.organizationservice.core.dto.filter;

import com.mealmap.organizationservice.entity.enums.OrganizationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationFilterDto {
    private Integer upn;

    private String name;

    private String legalAddress;

    private String phoneNumber;

    private String email;

    private OrganizationType type;
}
