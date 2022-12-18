package com.bibliyomani.standalone.bff.service;

import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.repository.BookRepository;
import com.bibliyomani.standalone.bff.repository.jdbc.NativeBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final NativeBookRepository nativeBookRepository;

    public void uploadBook(Book book) {
        bookRepository.save(book);
    }

    public Book fetchBook(String hash) {
        return bookRepository.findBookByHash(hash);
    }

    public List<Book> listAll() throws SQLException {
        return nativeBookRepository.listBooksWithoutContent();
    }
}
