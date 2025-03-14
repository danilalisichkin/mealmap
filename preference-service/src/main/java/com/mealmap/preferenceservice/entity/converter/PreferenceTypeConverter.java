package com.mealmap.preferenceservice.entity.converter;

import com.mealmap.preferenceservice.entity.enums.PreferenceType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class PreferenceTypeConverter implements AttributeConverter<PreferenceType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PreferenceType preferenceType) {
        return Optional.ofNullable(preferenceType)
                .map(PreferenceType::getId)
                .orElse(null);
    }

    @Override
    public PreferenceType convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(PreferenceType::fromId)
                .orElse(null);
    }
}
