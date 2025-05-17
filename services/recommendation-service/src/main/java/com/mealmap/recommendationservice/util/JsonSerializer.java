package com.mealmap.recommendationservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonSerializer {
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public String serialize(Object object) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
