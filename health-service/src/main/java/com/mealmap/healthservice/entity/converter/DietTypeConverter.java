package com.mealmap.healthservice.entity.converter;

import com.mealmap.healthservice.entity.enums.DietType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class DietTypeConverter implements AttributeConverter<DietType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DietType gender) {
        return Optional.ofNullable(gender)
                .map(DietType::getId)
                .orElse(null);
    }

    @Override
    public DietType convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(DietType::fromId)
                .orElse(null);
    }
}
