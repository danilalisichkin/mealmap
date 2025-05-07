package com.example.fileservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {
    public static MediaType getMediaType(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
        return switch (extension) {
            case "png" -> MediaType.IMAGE_PNG;
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "webp" -> MediaType.valueOf("image/webp");
            case "gif" -> MediaType.IMAGE_GIF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }

    public static String extractPrefix(String filename) {
        if (filename == null || !filename.contains("_")) {
            return "";
        }

        int underscoreIndex = filename.indexOf("_");

        return filename.substring(0, underscoreIndex);
    }
}
