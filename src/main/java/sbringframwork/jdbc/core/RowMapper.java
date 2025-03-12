package sbringframwork.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 将数据库查询结果集（ResultSet）中的每一个行数据映射（或转换）为开发者指定的Java对象类型 T 。
 */
public interface RowMapper<T> {
    /**
     * 将数据库查询结果集（ResultSet）中的每一个行数据映射（或转换）为开发者指定的Java对象类型 T 。
     * */
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
