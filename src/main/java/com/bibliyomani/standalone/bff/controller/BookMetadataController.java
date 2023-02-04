package com.bibliyomani.standalone.bff.controller;

import com.bibliyomani.standalone.bff.modal.BookMetadata;
import com.bibliyomani.standalone.bff.service.BookMetadataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/book-metadata")
@AllArgsConstructor
public class BookMetadataController {

    private final BookMetadataService bookMetadataService;

    @GetMapping
    public List<BookMetadata> listMetadata() throws SQLException {
        return bookMetadataService.listMetadata();
    }

    @GetMapping("/{bookId}")
    public BookMetadata findMetadataByBookId(@PathVariable Integer bookId) throws SQLException {
        return bookMetadataService.findMetadataByBookId(bookId);
    }
}
