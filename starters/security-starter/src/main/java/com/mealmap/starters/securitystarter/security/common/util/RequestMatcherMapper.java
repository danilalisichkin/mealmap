package com.mealmap.starters.securitystarter.security.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestMatcherMapper {
    public static RequestMatcher[] toAntRequestMatchers(List<String> paths) {
        return paths.stream()
                .map(AntPathRequestMatcher::new)
                .toArray(RequestMatcher[]::new);
    }
}
