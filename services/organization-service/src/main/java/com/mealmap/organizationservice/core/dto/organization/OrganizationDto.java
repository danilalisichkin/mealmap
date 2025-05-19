package com.mealmap.organizationservice.core.dto.organization;

import com.mealmap.organizationservice.entity.enums.OrganizationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Schema(description = "Информация об организации")
public class OrganizationDto {
    private Integer id;

    private Integer upn;

    private String name;

    private String imageUrl;

    private String legalAddress;

    private String phoneNumber;

    private String email;

    private OrganizationType type;

    private LocalDate createdAt;
}
