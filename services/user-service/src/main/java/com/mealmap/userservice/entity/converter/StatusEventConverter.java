package com.mealmap.userservice.entity.converter;

import com.mealmap.userservice.entity.enums.StatusEvent;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class StatusEventConverter implements AttributeConverter<StatusEvent, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEvent statusEvent) {
        return Optional.ofNullable(statusEvent)
                .map(StatusEvent::getId)
                .orElse(null);
    }

    @Override
    public StatusEvent convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(StatusEvent::fromId)
                .orElse(null);
    }
}
