package com.bibliyomani.standalone.bff.service;

import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void uploadBook(Book book) {
        bookRepository.save(book);
    }

    public Book fetchBook(String hash) {
        return bookRepository.findBookByHash(hash);
    }
}
