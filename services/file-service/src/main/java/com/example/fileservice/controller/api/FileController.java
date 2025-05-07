package com.example.fileservice.controller.api;

import com.example.fileservice.service.FileService;
import com.example.fileservice.util.FileUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;

    @PostMapping("/{filename}")
    public ResponseEntity<Void> upload(
            @PathVariable("filename") @NotBlank @Size(min = 3, max = 200) String filename,
            @RequestParam("file") MultipartFile file) {

        fileService.upload(filename, file);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> download(
            @PathVariable @NotBlank @Size(min = 3, max = 200) String filename) {

        byte[] file = fileService.getFile(filename);

        return ResponseEntity.status(HttpStatus.OK).contentType(FileUtils.getMediaType(filename)).body(file);
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<Void> delete(
            @PathVariable @NotBlank @Size(min = 3, max = 200) String filename) {

        fileService.delete(filename);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
