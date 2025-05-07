package com.example.fileservice.controller.api;

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
@RequestMapping("/files")
public class FileController {
    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("filename") @NotBlank @Size(min = 3, max = 200) String fileName) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> download(
            @PathVariable @NotBlank @Size(min = 3, max = 200) String filename) {

        return ResponseEntity.status(HttpStatus.OK).contentType(...).build();
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<String> delete(
            @PathVariable @NotBlank @Size(min = 3, max = 200) String filename) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
