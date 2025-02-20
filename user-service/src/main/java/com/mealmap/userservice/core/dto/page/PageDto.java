package com.mealmap.userservice.core.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    private int currentPage;

    private int pageSize;

    private int totalPages;

    private long totalElements;

    private List<T> items;
}
