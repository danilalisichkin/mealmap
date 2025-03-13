package com.example.organizationservice.entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class OrganizationTypeConverter implements AttributeConverter<OrganizationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrganizationType userRole) {
        return Optional.ofNullable(userRole)
                .map(OrganizationType::getId)
                .orElse(null);
    }

    @Override
    public OrganizationType convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(OrganizationType::fromId)
                .orElse(null);
    }
}
