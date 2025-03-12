package sbringframwork.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理从ResultSet中读出的所有列数据，成为T类型。
 */
public interface ResultSetExtractor<T> {
    /**
     * 处理从ResultSet中读出的所有列数据，成为T类型。
     * */
    T extractData(ResultSet rs) throws SQLException;
}
