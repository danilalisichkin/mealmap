package com.example.fileservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void upload(String fileName, MultipartFile file);

    byte[] getFile(String fileName);

    void delete(String fileName);
}
