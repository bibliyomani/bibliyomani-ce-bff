package com.bibliyomani.standalone.bff.controller;

import com.bibliyomani.standalone.bff.modal.BookMetadata;
import com.bibliyomani.standalone.bff.repository.BookMetadataRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metadata")
@AllArgsConstructor
public class BookMetadataController {

    private final BookMetadataRepository bookMetadataRepository;

    @GetMapping
    public BookMetadata findByHash(@RequestParam String hash) {
        return bookMetadataRepository.findByHash(hash);
    }

    @GetMapping("/all")
    public List<BookMetadata> findAll() {
        return bookMetadataRepository.findAll();
    }

    @PutMapping
    public boolean updateMetadata(@RequestBody BookMetadata metadata) {
        final String hash = metadata.getHash();
        final int last = metadata.getLast();
        final BookMetadata bookMetadata = bookMetadataRepository.findByHash(hash);
        bookMetadata.setLast(last);

        return bookMetadataRepository.save(bookMetadata) != null;
    }
}
