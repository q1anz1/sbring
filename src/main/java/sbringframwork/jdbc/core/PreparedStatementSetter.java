package sbringframwork.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * PreparedStatement 是 JDBC API 中的一个关键类，它代表了一个预编译的 SQL 语句。
 * 与普通的 Statement 相比，PreparedStatement 的主要优势在于可以防止 SQL 注入攻击，并且通常性能更好，因为它可以被数据库预编译并缓存起来。
 * PreparedStatementSetter 接口的 setValues 方法允许开发者以一种编程的方式动态地设置 SQL 语句中的参数值。
 * 这在执行参数化查询时非常有用，因为你可以根据运行时的情况来设置这些值。
 */
public interface PreparedStatementSetter {
    void setValues(PreparedStatement ps) throws SQLException;
}
