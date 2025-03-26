package com.mealmap.recommendationservice.client.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FeignResponseJsonReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static <T> T readFromBody(Response response, Class<T> responseType) {
        return mapper.readValue(response.body().asInputStream(), responseType);
    }
}
