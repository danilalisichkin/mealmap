package com.example.fileservice.validator;

import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.InternalErrorException;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.errors.ErrorResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.fileservice.core.message.ApplicationMessages.FILE_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class FileValidator {
    private final MinioClient minioClient;

    public void validateFileUniqueness(String bucketName, String fileName) {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );

            throw new ConflictException(FILE_ALREADY_EXISTS.formatted(fileName));
        } catch (ConflictException e) {
            throw e;
        } catch (ErrorResponseException e) {
            if ("NoSuchKey".equals(e.errorResponse().code()) || "NoSuchObject".equals(e.errorResponse().code())) {
                return;
            }
            throw new InternalErrorException(e);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
