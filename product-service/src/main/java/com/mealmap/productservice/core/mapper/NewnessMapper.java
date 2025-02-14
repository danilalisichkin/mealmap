package com.mealmap.productservice.core.mapper;

import com.mealmap.productservice.config.NewnessConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class NewnessMapper {
    private final NewnessConfig newnessConfig;

    public boolean mapCreatedAt(LocalDate createdAt) {
        if (createdAt == null || newnessConfig.getPeriod() == null) {
            return false;
        }

        return createdAt.plusDays(newnessConfig.getPeriod()).isAfter(LocalDate.now());
    }
}
