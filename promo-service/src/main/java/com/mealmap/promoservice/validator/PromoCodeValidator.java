package com.mealmap.promoservice.validator;

import com.mealmap.promoservice.exception.BadRequestException;
import com.mealmap.promoservice.exception.ConflictException;
import com.mealmap.promoservice.repository.PromoCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.mealmap.promoservice.core.message.ApplicationMessages.PROMO_CODE_ALREADY_EXISTS;
import static com.mealmap.promoservice.core.message.ApplicationMessages.PROMO_CODE_END_DATE_BEFORE_START_DATE;
import static com.mealmap.promoservice.core.message.ApplicationMessages.PROMO_CODE_EXPIRED;
import static com.mealmap.promoservice.core.message.ApplicationMessages.PROMO_CODE_LIMIT_REACHED;

@Component
@RequiredArgsConstructor
public class PromoCodeValidator {
    private final PromoCodeRepository promoCodeRepository;

    public void validateCodeUniqueness(String code) {
        if (promoCodeRepository.existsById(code)) {
            throw new ConflictException(PROMO_CODE_ALREADY_EXISTS.formatted(code));
        }
    }

    public void validatePromoCodeExpiration(LocalDate endDate) {
        if (endDate.isBefore(LocalDate.now())) {
            throw new BadRequestException(PROMO_CODE_EXPIRED);
        }
    }

    public void validatePromoCodeDateRange(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new BadRequestException(PROMO_CODE_END_DATE_BEFORE_START_DATE);
        }
    }

    public void validatePromoCodeApplicationLimit(Long limit) {
        if (limit <= 0) {
            throw new BadRequestException(PROMO_CODE_LIMIT_REACHED);
        }
    }
}
