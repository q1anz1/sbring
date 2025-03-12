package sbringframwork.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 提供一个回调机制，允许开发者在PreparedStatement对象上执行自定义的数据库操作，并处理操作的结果。
 */
public interface PreparedStatementCallback<T> {
    T doInPreparedStatement(PreparedStatement ps) throws SQLException;
}
