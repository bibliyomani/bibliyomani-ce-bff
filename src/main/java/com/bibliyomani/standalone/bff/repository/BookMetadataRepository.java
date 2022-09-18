package com.bibliyomani.standalone.bff.repository;


import com.bibliyomani.standalone.bff.modal.BookMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMetadataRepository extends JpaRepository<BookMetadata, Integer> {
    BookMetadata findByHash(String hash);
}