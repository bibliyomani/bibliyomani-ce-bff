package com.bibliyomani.standalone.bff.service;

import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.repository.BookRepository;
import com.bibliyomani.standalone.bff.repository.jdbc.NativeBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final NativeBookRepository nativeBookRepository;

    public long updateLastInteraction(Integer bookId) {
        final Book bookByBookId = bookRepository.findBookByBookId(bookId);
        final long lastInteraction = System.currentTimeMillis();
        bookByBookId.setLastInteraction(lastInteraction);

        final Book saved = bookRepository.save(bookByBookId);
        return saved.getLastInteraction();
    }

    public void updateLastRead(final Integer bookId,
                               final Integer lastRead) {
        final Book bookByBookId = bookRepository.findBookByBookId(bookId);
        final int read = bookByBookId.getRead();

        if (lastRead > read) {
            bookByBookId.setRead(lastRead);
            bookRepository.save(bookByBookId);
        }

    }
}