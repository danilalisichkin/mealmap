package com.mealmap.organizationservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String ORGANIZATION_NOT_FOUND = "организация %s не найдена";
    public static final String SUPPLIER_NOT_FOUND = "поставщик %s не найден";
    public static final String ORGANIZATION_WITH_UPN_ALREADY_EXISTS = "организация с УНП %s уже существует";
}
