package com.bibliyomani.standalone.bff.controller;

import com.bibliyomani.standalone.bff.repository.jdbc.BookJdbcRepository;
import com.bibliyomani.standalone.bff.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookJdbcRepository bookJdbcRepository;
    private final UploadService uploadService;

    @GetMapping
    public ResponseEntity<InputStreamResource> fetchBook(@RequestParam String hash) throws SQLException {
        final byte[] contentByHash = bookJdbcRepository.findContentByHash(hash);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(contentByHash);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean uploadBook(@RequestParam MultipartFile file) throws IOException {
        return uploadService.uploadBook(file);
    }
}