package com.bibliyomani.standalone.bff.service;

import com.bibliyomani.standalone.bff.factory.EncodingFactory;
import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UploadService {

    private final BookRepository bookRepository;
    private final EncodingFactory encodingFactory;

    public boolean uploadBook(MultipartFile file) throws IOException {
        final byte[] content = file.getBytes();
        final String originalFilename = file.getOriginalFilename();
        final String hash = encodingFactory.valueOf(originalFilename);

        Book book = new Book();
        book.setLast(0);
        book.setName(originalFilename);
        book.setHash(hash);
        book.setContent(content);

        return bookRepository.save(book) != null;
    }
}
