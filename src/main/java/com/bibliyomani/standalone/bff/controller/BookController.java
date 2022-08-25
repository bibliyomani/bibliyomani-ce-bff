package com.bibliyomani.standalone.bff.controller;

import com.bibliyomani.standalone.bff.repository.BookRepository;
import com.bibliyomani.standalone.bff.repository.jdbc.BookJdbcRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookJdbcRepository bookJdbcRepository;

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


}