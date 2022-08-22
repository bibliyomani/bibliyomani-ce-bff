package com.bibliyomani.standalone.bff.controller;

import com.bibliyomani.standalone.bff.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;


//    @GetMapping
//    public ResponseEntity<InputStreamResource> fetchBook(@RequestParam String hash) {
//        BookJDBCPojo pojo = bookRepository.fetchBookMetadata(hash);
//        String path = pojo.getPath();
//        InputStream inputStream = dropboxRepository.downloadFile(path);
//
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
//                .contentType(MediaType.TEXT_PLAIN)
//                .body(new InputStreamResource(null));
//    }
}