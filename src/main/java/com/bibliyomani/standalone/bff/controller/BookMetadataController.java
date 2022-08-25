package com.bibliyomani.standalone.bff.controller;

import com.bibliyomani.standalone.bff.modal.BookMetadata;
import com.bibliyomani.standalone.bff.repository.BookMetadataRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metadata")
@AllArgsConstructor
public class BookMetadataController {

    private final BookMetadataRepository bookMetadataRepository;

    @GetMapping
    public List<BookMetadata> findAll() {
        return bookMetadataRepository.findAll();
    }
}
