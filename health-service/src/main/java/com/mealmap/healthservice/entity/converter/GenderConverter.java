package com.mealmap.healthservice.entity.converter;

import com.mealmap.healthservice.entity.enums.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return Optional.ofNullable(gender)
                .map(Gender::getId)
                .orElse(null);
    }

    @Override
    public Gender convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(Gender::fromId)
                .orElse(null);
    }
}
