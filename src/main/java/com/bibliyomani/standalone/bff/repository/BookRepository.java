package com.bibliyomani.standalone.bff.repository;


import com.bibliyomani.standalone.bff.modal.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByHash(String hash);
    Book findBookByBookId(Integer bookId);
}