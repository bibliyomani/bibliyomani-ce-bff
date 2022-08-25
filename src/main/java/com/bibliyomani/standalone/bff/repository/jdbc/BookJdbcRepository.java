package com.bibliyomani.standalone.bff.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
@AllArgsConstructor
public class BookJdbcRepository {

    private final DataSource dataSource;
    private final static String QUERY = "select content from book where hash = ?";

    public byte[] findContentByHash(final String hash) throws SQLException {
        byte[] content = {};

        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    QUERY,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
                preparedStatement.setString(1, hash);

                final ResultSet rs = preparedStatement.executeQuery();
                rs.setFetchSize(500);

                while (rs.next())
                    content = rs.getBytes(1);


                rs.close();
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }

        return content;
    }

}
