package com.example.fileservice.service.impl;

import com.example.fileservice.properties.MinioProperties;
import com.example.fileservice.service.FileService;
import com.example.fileservice.util.FileUtils;
import com.example.fileservice.validator.FileValidator;
import com.mealmap.starters.exceptionstarter.exception.InternalErrorException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

import static com.example.fileservice.core.message.ApplicationMessages.BUCKET_NOT_FOUND;
import static com.example.fileservice.core.message.ApplicationMessages.FILE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheResolver = "fileCacheResolver")
public class FileServiceImpl implements FileService {
    private final FileValidator fileValidator;

    private final MinioClient minioClient;

    private final MinioProperties minioProperties;

    @Override
    @CachePut(key = "#fileName")
    public void upload(String fileName, MultipartFile file) {
        String bucket = getBucket(fileName);
        fileValidator.validateFileUniqueness(bucket, fileName);

        try (InputStream is = file.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .stream(is, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
        } catch (Exception e) {
            throw new InternalErrorException("Ошибка загрузки файла в MinIO", e);
        }
    }

    @Override
    @Cacheable(key = "#fileName")
    public byte[] getFile(String fileName) {
        String bucket = getBucket(fileName);

        try (InputStream is = minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(fileName)
                .build())) {
            return is.readAllBytes();
        } catch (ErrorResponseException e) {
            if ("NoSuchKey".equals(e.errorResponse().code())) {
                throw new ResourceNotFoundException(FILE_NOT_FOUND.formatted(fileName));
            }
            throw new InternalErrorException("Ошибка получения файла", e);
        } catch (Exception e) {
            throw new InternalErrorException("Ошибка получения файла", e);
        }
    }

    @Override
    @CacheEvict(key = "#fileName")
    public void delete(String fileName) {
        String bucket = getBucket(fileName);

        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .build());
        } catch (Exception e) {
            throw new InternalErrorException("Ошибка удаления файла из MinIO", e);
        }
    }

    private String getBucket(String fileName) {
        var requestedBucket = FileUtils.extractPrefix(fileName);

        if (!minioProperties.getBuckets().contains(requestedBucket)) {
            throw new ResourceNotFoundException(BUCKET_NOT_FOUND.formatted(requestedBucket));
        } else {
            return requestedBucket;
        }
    }
}
