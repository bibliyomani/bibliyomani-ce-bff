package com.bibliyomani.standalone.bff.service;

import com.bibliyomani.standalone.bff.factory.EncodingFactory;
import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
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
        final PDDocument doc = PDDocument.load(content);
        final int total = doc.getNumberOfPages();

        final String originalFilename = file.getOriginalFilename();
        final String hash = encodingFactory.valueOf(originalFilename);

        final Book book = Book.builder()
                .name(originalFilename)
                .hash(hash)
                .content(content)
                .total(total)
                .last(0)
                .build();

        return bookRepository.save(book) != null;
    }
}
