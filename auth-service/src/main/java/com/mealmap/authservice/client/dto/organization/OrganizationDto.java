package com.mealmap.authservice.client.dto.organization;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrganizationDto {
    private Integer id;

    private Integer upn;

    private String name;

    private String legalAddress;

    private String phoneNumber;

    private String email;

    private OrganizationType type;

    private LocalDate createdAt;
}
