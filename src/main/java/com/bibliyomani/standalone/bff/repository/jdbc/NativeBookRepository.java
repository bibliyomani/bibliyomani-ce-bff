package com.bibliyomani.standalone.bff.repository.jdbc;

import com.bibliyomani.standalone.bff.modal.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class NativeBookRepository {

    private final DataSource dataSource;
    private final static String QUERY = "select book_id, name, hash, last from book";

    public List<Book> listBooksWithoutContent() throws SQLException {
        final List<Book> books = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    QUERY,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

                final ResultSet rs = preparedStatement.executeQuery();
                rs.setFetchSize(500);

                while (rs.next()) {
                    Book book = new Book();
                    book.setBookId(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setHash(rs.getString(3));
                    book.setLast(rs.getInt(4));
                    books.add(book);
                }

                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }

        return books;
    }

}
