package com.mealmap.orderservice.client.decoder;

import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.mealmap.orderservice.client.util.FeignResponseErrorExtractor.extractErrorDetail;
import static com.mealmap.orderservice.core.message.ApplicationMessages.PROMO_CODE_ALREADY_APPLIED;

@Component
public class PromoStatApiErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        String errorDetail = extractErrorDetail(response);

        return switch (HttpStatus.valueOf(response.status())) {
            case BAD_REQUEST -> new BadRequestException(errorDetail);
            case NOT_FOUND -> new ResourceNotFoundException(errorDetail);
            case CONFLICT -> new ConflictException(PROMO_CODE_ALREADY_APPLIED);
            default -> defaultErrorDecoder.decode(s, response);
        };
    }
}
