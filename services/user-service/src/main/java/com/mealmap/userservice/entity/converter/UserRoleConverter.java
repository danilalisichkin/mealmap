package com.mealmap.userservice.entity.converter;

import com.mealmap.userservice.entity.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return Optional.ofNullable(role)
                .map(Role::getId)
                .orElse(null);
    }

    @Override
    public Role convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(Role::fromId)
                .orElse(null);
    }
}
