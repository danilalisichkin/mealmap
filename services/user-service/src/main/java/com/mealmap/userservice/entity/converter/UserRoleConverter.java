package com.mealmap.userservice.entity.converter;

import com.mealmap.userservice.entity.enums.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRole userRole) {
        return Optional.ofNullable(userRole)
                .map(UserRole::getId)
                .orElse(null);
    }

    @Override
    public UserRole convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(UserRole::fromId)
                .orElse(null);
    }
}
