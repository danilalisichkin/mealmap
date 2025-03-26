package com.mealmap.recommendationservice.client.util;


import feign.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ProblemDetail;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FeignResponseErrorExtractor {
    @SneakyThrows
    public static String extractErrorDetail(Response response) {
        return FeignResponseJsonReader.readFromBody(response, ProblemDetail.class).getDetail();
    }
}
