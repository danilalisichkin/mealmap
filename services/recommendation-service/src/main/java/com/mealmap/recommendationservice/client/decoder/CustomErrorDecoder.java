package com.mealmap.recommendationservice.client.decoder;

import com.mealmap.recommendationservice.exception.BadRequestException;
import com.mealmap.recommendationservice.exception.ConflictException;
import com.mealmap.recommendationservice.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.mealmap.recommendationservice.client.util.FeignResponseErrorExtractor.extractErrorDetail;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        String errorDetail = extractErrorDetail(response);

        return switch (HttpStatus.valueOf(response.status())) {
            case BAD_REQUEST -> new BadRequestException(errorDetail);
            case NOT_FOUND -> new ResourceNotFoundException(errorDetail);
            case CONFLICT -> new ConflictException(errorDetail);
            default -> defaultErrorDecoder.decode(s, response);
        };
    }
}
