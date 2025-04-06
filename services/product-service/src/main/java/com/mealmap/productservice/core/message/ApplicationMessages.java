package com.mealmap.productservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String PRODUCT_NOT_FOUND = "продукт %s не найден";
    public static final String PRODUCT_WITH_NAME_ALREADY_EXISTS = "продукт с приведенным названием уже существует";
    public static final String CATEGORIES_NOT_FOUND = "категории %s не найдены";
    public static final String CATEGORY_NOT_FOUND = "категория %s не найдена";
    public static final String CATEGORY_WITH_NAME_ALREADY_EXISTS = "категория с приведенным названием уже существует";
}
