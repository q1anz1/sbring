package sbringframwork.jdbc.core;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 提供一个回调机制，允许开发者在JDBC Statement对象上执行自定义的数据库操作，并处理操作的结果。
 */
public interface StatementCallback<T> {

    /**
     * 提供一个回调机制，允许开发者在JDBC Statement对象上执行自定义的数据库操作，并处理操作的结果。
     */
    T doInStatement(Statement statement) throws SQLException;
}
