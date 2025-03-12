package sbringframwork.jdbc.datasource;

import cn.hutool.core.lang.Assert;

import java.sql.Connection;

/**
 * 定义如何获取和释放数据库连接。
 */
public class SimpleConnectionHandler implements ConnectionHandler {
    private final Connection connection;

    public SimpleConnectionHandler(Connection connection) {
        Assert.notNull(connection, "Connection 不能为空。");
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
