package com.bibliyomani.standalone.bff.controller;

import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.service.BookService;
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

    private final UploadService uploadService;
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<InputStreamResource> fetchBook(@RequestParam String hash) throws SQLException {
        final Book book = bookService.fetchBook(hash);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(book.getContent());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean uploadBook(@RequestParam(value = "books") MultipartFile[] books) throws IOException {
        for (MultipartFile book : books) {
            uploadService.uploadBook(book);
        }

        return false;
    }
}