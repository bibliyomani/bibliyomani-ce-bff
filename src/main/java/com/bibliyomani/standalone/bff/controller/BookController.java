package com.bibliyomani.standalone.bff.controller;

import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.repository.BookRepository;
import com.bibliyomani.standalone.bff.service.BookService;
import com.bibliyomani.standalone.bff.service.CompressionService;
import com.bibliyomani.standalone.bff.service.UploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final UploadService uploadService;
    private final BookService bookService;
    private final CompressionService compressionService;
    private final BookRepository bookRepository;

    @GetMapping("/{bookId}")
    public ResponseEntity<InputStreamResource> fetchBook(@PathVariable Integer bookId) {
        final Book book = bookRepository.findBookByBookId(bookId);
        final byte[] compressed = book.getContent();
        final int decompressedSize = book.getDecompressedSize();
        final int compressedSize = book.getCompressedSize();
        final byte[] decompressed = compressionService.decompress(compressed, decompressedSize, compressedSize);

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decompressed);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean uploadBook(@RequestParam(value = "books") MultipartFile[] books) throws IOException {
        for (MultipartFile book : books) uploadService.uploadBook(book);
        log.info("upload has done");
        return false;
    }

    @PatchMapping("/{bookId}")
    public long updateLastInteraction(@PathVariable Integer bookId) {
        return bookService.updateLastInteraction(bookId);
    }

    @PutMapping("/{bookId}")
    public void updateLastReadBook(@PathVariable Integer bookId,
                                   @RequestParam Integer lastRead) {
        bookService.updateLastRead(bookId, lastRead);
    }
}