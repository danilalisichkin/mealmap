package com.example.fileservice.core.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationMessages {
    public static final String FILE_NOT_FOUND = "файл %s не найден";
    public static final String BUCKET_NOT_FOUND = "бакет для файла с префиксом '%s' не найден";
    public static final String FILE_ALREADY_EXISTS = "файл %s уже существует";
}
