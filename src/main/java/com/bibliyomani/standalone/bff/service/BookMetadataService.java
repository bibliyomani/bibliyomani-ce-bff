package com.bibliyomani.standalone.bff.service;

import com.bibliyomani.standalone.bff.modal.BookMetadata;
import com.bibliyomani.standalone.bff.repository.jdbc.NativeBookMetadataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class BookMetadataService {

    private final NativeBookMetadataRepository nativeBookMetadataRepository;

    public List<BookMetadata> listMetadata() throws SQLException {
        return nativeBookMetadataRepository.listMetadata();
    }

    public BookMetadata findMetadataByBookId(final Integer bookId) throws SQLException {
        return nativeBookMetadataRepository.findMetadataByBookId(bookId);
    }
}