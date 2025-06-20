package com.mealmap.productservice.util;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ElasticsearchPageBuilder {
    public static <T> Page<T> buildPage(SearchResponse<T> response, Pageable pageable) {
        List<T> content = response.hits().hits().stream()
                .map(Hit::source)
                .toList();

        long totalElements = response.hits().total().value();

        return new PageImpl<>(content, pageable, totalElements);
    }
}
