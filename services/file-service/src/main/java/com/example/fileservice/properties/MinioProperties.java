package com.example.fileservice.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private final String url;
    private final String accessKey;
    private final String secretKey;
    private final List<String> buckets;
}
