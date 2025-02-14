package com.mealmap.productservice.core.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class PageDto<T> {
    int currentPage;

    int pageSize;

    int totalPages;

    long totalElements;

    List<T> items;
}
