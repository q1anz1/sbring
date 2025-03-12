package sbringframwork.jdbc.datasource;

import java.sql.Connection;

/**
 * 用于定义如何获取和释放数据库连接。
 */
public interface ConnectionHandler {
    Connection getConnection();

    default void releaseConnection(Connection con) {
    }
}
