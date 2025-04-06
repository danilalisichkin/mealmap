package com.mealmap.promoservice.validator;

import com.mealmap.promoservice.exception.ConflictException;
import com.mealmap.promoservice.repository.PromoStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.mealmap.promoservice.core.message.ApplicationMessages.PROMO_STAT_FOR_USER_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class PromoStatValidator {
    private final PromoStatRepository promoStatRepository;

    public void validateStatUniqueness(String promoCode, String userId) {
        if (promoStatRepository.existsByPromoCodeAndUserId(promoCode, userId)) {
            throw new ConflictException(PROMO_STAT_FOR_USER_ALREADY_EXISTS.formatted(promoCode, userId));
        }
    }
}
