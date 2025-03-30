package sbringframwork.jdbc.exception;

import java.sql.SQLException;

/**
 * 异常。
 */
public class CannotGetJdbcConnectionException extends RuntimeException {

    public CannotGetJdbcConnectionException(String message) {
        super(message);
    }

    public CannotGetJdbcConnectionException(String message, SQLException ex) {
        super(message, ex);
    }
}