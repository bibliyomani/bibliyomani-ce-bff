package com.bibliyomani.standalone.bff.repository.jdbc;

import com.bibliyomani.standalone.bff.modal.Book;
import com.bibliyomani.standalone.bff.modal.BookMetadata;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class NativeBookMetadataRepository {

    private final DataSource dataSource;
    private final static String QUERY = "select book_id, name, hash, last, total, size from book";
    private final static String FIND_SPECIFIC_METADATA_QUERY = "select book_id, name, hash, last, total, size from book where book_id = ?";

    public List<BookMetadata> listMetadata() throws SQLException {
        final List<BookMetadata> metadataList = new ArrayList<>();

        try (final Connection connection = dataSource.getConnection()) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(QUERY,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

                final ResultSet rs = preparedStatement.executeQuery();
                rs.setFetchSize(500);

                while (rs.next()) {
                    final BookMetadata bookMetadata = BookMetadata.builder()
                            .bookId(rs.getInt(1))
                            .name(rs.getString(2))
                            .hash(rs.getString(3))
                            .last(rs.getInt(4))
                            .total(rs.getInt(5))
                            .size(rs.getString(6))
                            .build();
                    metadataList.add(bookMetadata);
                }

                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }

        return metadataList;
    }

    public BookMetadata findMetadataByBookId(final Integer bookId) throws SQLException {
        final BookMetadata.BookMetadataBuilder builder = BookMetadata.builder();

        try (final Connection connection = dataSource.getConnection()) {
            connection.setReadOnly(true);
            try (final PreparedStatement preparedStatement = connection.prepareStatement(FIND_SPECIFIC_METADATA_QUERY,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
                preparedStatement.setInt(1, bookId);

                final ResultSet rs = preparedStatement.executeQuery();
                rs.setFetchSize(1);

                if (rs.first()) {
                    builder
                            .bookId(rs.getInt(1))
                            .name(rs.getString(2))
                            .hash(rs.getString(3))
                            .last(rs.getInt(4))
                            .total(rs.getInt(5))
                            .size(rs.getString(6))
                            .build();
                }

                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }

        return builder.build();
    }
}
