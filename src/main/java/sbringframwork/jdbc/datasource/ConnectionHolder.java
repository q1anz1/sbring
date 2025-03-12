package sbringframwork.jdbc.datasource;

import cn.hutool.core.lang.Assert;

import java.sql.Connection;

/**
 * 一个用于封装和管理数据库连接（Connection）的对象。
 */
public class ConnectionHolder {
    private ConnectionHandler connectionHandler;

    private Connection currentConnection;

    public ConnectionHolder(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public ConnectionHolder(Connection connection) {
        this.connectionHandler = new SimpleConnectionHandler(connection);
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    protected boolean hasConnection() {
        return this.connectionHandler != null;
    }

    protected void setConnection(Connection connection) {
        if (null != this.currentConnection) {
            if (null != this.connectionHandler) {
                this.connectionHandler.releaseConnection(this.currentConnection);
            }
            this.currentConnection = null;
        }
        if (null != connection) {
            this.connectionHandler = new SimpleConnectionHandler(connection);
        } else {
            this.connectionHandler = null;
        }
    }

    protected Connection getConnection() {
        Assert.notNull(this.connectionHandler, "Active connection is required.");
        if (null == this.currentConnection) {
            this.currentConnection = this.connectionHandler.getConnection();
        }
        return this.currentConnection;
    }
}
