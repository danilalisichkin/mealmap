package com.mealmap.authservice.client.decoder;

import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.mealmap.authservice.client.util.FeignResponseErrorExtractor.extractErrorDetail;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        String errorDetail = extractErrorDetail(response);

        return switch (HttpStatus.valueOf(response.status())) {
            case NOT_FOUND -> new ResourceNotFoundException(errorDetail);
            default -> defaultErrorDecoder.decode(s, response);
        };
    }
}
