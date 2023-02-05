package com.bibliyomani.standalone.bff.service;

import com.bibliyomani.standalone.bff.factory.EncodingFactory;
import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

@Service
@AllArgsConstructor
public class UploadService {

    private final BookRepository bookRepository;
    private final EncodingFactory encodingFactory;

    public boolean uploadBook(final MultipartFile file) throws IOException {
        final byte[] content = file.getBytes();
        final PDDocument doc = PDDocument.load(content);
        final int total = doc.getNumberOfPages();

        final String originalFilename = file.getOriginalFilename();
        final String hash = encodingFactory.valueOf(originalFilename);
        final String size = humanReadableByteCountSI(content.length);
        final long unixTimestamp = System.currentTimeMillis();

        final Book book = Book.builder()
                .name(originalFilename)
                .hash(hash)
                .content(content)
                .total(total)
                .last(0)
                .size(size)
                .lastInteraction(unixTimestamp)
                .build();

        return bookRepository.save(book) != null;
    }

    public String humanReadableByteCountSI(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        final CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (bytes <= -999_950 || bytes >= 999_950) {
            bytes /= 1000;
            ci.next();
        }
        return String.format("%.1f %cB", bytes / 1000.0, ci.current());
    }
}
