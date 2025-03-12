package sbringframwork.jdbc.support;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import sbringframwork.jdbc.UncategorizedSQLException;
import sbringframwork.jdbc.core.*;
import sbringframwork.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JdbcTemplate extends JdbcAccessor implements JdbcOperations {

    //设置从数据库中一次获取的行数（即每次从数据库拉取到客户端的行数）。默认情况下，fetchSize的值是-1，这意味着使用数据库驱动或JDBC连接的默认设置。
    private int fetchSize = -1;

    //限制查询结果集的最大行数。默认情况下，maxRows的值也是-1，表示没有行数限制。
    private int maxRows = -1;

    // 属性用于设置查询的超时时间（以秒为单位）。如果查询在指定的超时时间内没有完成，将抛出一个SQLException，表明查询已超时。
    private int queryTimeout = -1;

    public JdbcTemplate() {
    }

    public JdbcTemplate(DataSource dataSource) {
        setDataSource(dataSource);
        afterPropertiesSet();
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    private <T> T execute(StatementCallback<T> action, boolean closeResources) {
        Connection con = DataSourceUtils.getConnection(obtainDataSource());
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            applyStatementSettings(stmt);
            return action.doInStatement(stmt);
        } catch (SQLException e) {
            String sql = getSql(action);
            JdbcUtils.closeStatement(stmt);
            stmt = null;
            throw translateException("ConnectionCallback", sql, e);
        } finally {
            if (closeResources) {
                JdbcUtils.closeStatement(stmt);
            }
        }
    }

    protected void applyStatementSettings(Statement stat) throws SQLException {
        int fetchSize = getFetchSize();
        if (fetchSize != -1) {
            stat.setFetchSize(fetchSize);
        }
        int maxRows = getMaxRows();
        if (maxRows != -1) {
            stat.setMaxRows(maxRows);
        }

    }

    private static String getSql(Object sqlProvider) {
        if (sqlProvider instanceof SqlProvider) {
            return ((SqlProvider) sqlProvider).getSql();
        } else {
            return null;
        }
    }

    protected UncategorizedSQLException translateException(String task, String sql, SQLException ex) {
        return new UncategorizedSQLException(task, sql, ex);
    }

    @Override
    public <T> T execute(StatementCallback<T> action) {
        return null;
    }


    /*
     * execute
     * */
    @Override
    public void execute(String sql) {

        class ExecuteStatementCallback implements StatementCallback<Object>, SqlProvider {

            @Override
            public Object doInStatement(Statement statement) throws SQLException {
                statement.execute(sql);
                return null;
            }

            @Override
            public String getSql() {
                return sql;
            }
        }
        execute(new ExecuteStatementCallback(), true);
    }

    @Override
    public <T> T query(String sql, ResultSetExtractor<T> res) {
        Assert.notNull(sql, "SQL must not be null");
        Assert.notNull(res, "ResultSetExtractor must be null");

        class QueryStatementCallback implements StatementCallback<T>, SqlProvider {

            @Override
            public String getSql() {
                return sql;
            }

            @Override
            public T doInStatement(Statement statement) throws SQLException {
                ResultSet rs = statement.executeQuery(sql);
                return res.extractData(rs);
            }
        }
        return execute(new QueryStatementCallback(), true);
    }

    @Override
    public <T> T query(String sql, Object[] args, ResultSetExtractor<T> rse) {
        return query(sql, newArgPreparedStatementSetter(args), rse);
    }

    protected PreparedStatementSetter newArgPreparedStatementSetter(Object[] args) {
        return new ArgumentPreparedStatementSetter(args);
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return result(query(sql, new RowMapperResultSetExtractor<>(rowMapper)));
    }

    private static <T> T result(T result) {
        Assert.state(null != result, "No result");
        return result;
    }

    @Override
    public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
        return result(query(sql, args, new RowMapperResultSetExtractor<>(rowMapper)));
    }

    @Override
    public <T> T query(String sql, PreparedStatementSetter pss, ResultSetExtractor<T> rse) {
        return query(new SimplePreparedStatementCreator(sql), pss, rse);
    }

    public <T> T query(PreparedStatementCreator preparedStatementCreator,
                       final PreparedStatementSetter preparedStatementSetter,
                       final ResultSetExtractor<T> resultSetExtractor) {

        Assert.notNull(resultSetExtractor, "ResultSetExtractor must not be null");

        return execute(preparedStatementCreator, preparedStatement -> {
            ResultSet resultSet = null;
            try {
                if (preparedStatementSetter != null) {
                    preparedStatementSetter.setValues(preparedStatement);
                }
                resultSet = preparedStatement.executeQuery();
                return resultSetExtractor.extractData(resultSet);
            } finally {
                JdbcUtils.closeResultSet(resultSet);
            }
        }, true);
    }

    private <T> T execute(PreparedStatementCreator preparedStatementCreator, PreparedStatementCallback<T> preparedStatementCallback, boolean closeResources) {
        Assert.notNull(preparedStatementCreator, "PreparedStatementCreator must not be null");
        Assert.notNull(preparedStatementCallback, "Callback object must not be null");

        Connection connection = DataSourceUtils.getConnection(obtainDataSource());
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = preparedStatementCreator.createPreparedStatement(connection);
            applyStatementSettings(preparedStatement);
            T result = preparedStatementCallback.doInPreparedStatement(preparedStatement);
            return result;
        } catch (SQLException ex) {
            String sql = getSql(preparedStatementCreator);
            preparedStatementCreator = null;
            JdbcUtils.closeStatement(preparedStatement);
            preparedStatement = null;
            DataSourceUtils.releaseConnection(connection, getDataSource());
            connection = null;
            throw translateException("PreparedStatementCallback", sql, ex);
        } finally {
            if (closeResources) {
                JdbcUtils.closeStatement(preparedStatement);
                DataSourceUtils.releaseConnection(connection, getDataSource());
            }
        }
    }

    private static class SimplePreparedStatementCreator implements PreparedStatementCreator, SqlProvider {

        private final String sql;

        public SimplePreparedStatementCreator(String sql) {
            this.sql = sql;
        }


        @Override
        public String getSql() {
            return this.sql;
        }

        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            return con.prepareStatement(this.sql);
        }
    }



    /*
    * List<Map<String, Object>> queryForList(String sql)
    * */
    @Override
    public List<Map<String, Object>> queryForList(String sql) {
        return query(sql, getColumnMapRowMapper());
    }

    protected RowMapper<Map<String, Object>> getColumnMapRowMapper() {
        return new ColumnMapRowMapper();
    }

    /*
    * List<T> queryForList(String sql, Class<T> elementType)
    * */
    @Override
    public <T> List<T> queryForList(String sql, Class<T> elementType) {
        return query(sql, getSingleColumnRowMapper(elementType));
    }

    protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
        return new SingleColumnRowMapper<>(requiredType);
    }

    /*
    * List<T> queryForList(String sql, Class<T> elementType, Object... args)
    * */
    @Override
    public <T> List<T> queryForList(String sql, Class<T> elementType, Object... args) {
        return query(sql, args, getSingleColumnRowMapper(elementType));
    }

    /*
    * List<Map<String, Object>> queryForList(String sql, Object... args)
    * */
    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... args) {
        return query(sql, args, getColumnMapRowMapper());
    }

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper) {
        List<T> results = query(sql, rowMapper);
        if (CollUtil.isEmpty(results)) {
            throw new RuntimeException("Incorrect result size: expected 1, actual 0");
        }
        if (results.size() > 1) {
            throw new RuntimeException("Incorrect result size: expected 1, actual " + results.size());
        }
        return results.iterator().next();
    }

    @Override
    public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) {
        List<T> results = query(sql, args, new RowMapperResultSetExtractor<>(rowMapper, 1));
        if (CollUtil.isEmpty(results)) {
            throw new RuntimeException("Incorrect result size: expected 1, actual 0");
        }
        if (results.size() > 1) {
            throw new RuntimeException("Incorrect result size: expected 1, actual " + results.size());
        }
        return results.iterator().next();
    }

    /*
    * T queryForObject(String sql, Class<T> requiredType)
    * */
    @Override
    public <T> T queryForObject(String sql, Class<T> requiredType) {
        return queryForObject(sql, getSingleColumnRowMapper(requiredType));
    }

    @Override
    public Map<String, Object> queryForMap(String sql) {
        return result(queryForObject(sql, getColumnMapRowMapper()));
    }

    @Override
    public Map<String, Object> queryForMap(String sql, Object... args) {
        return result(queryForObject(sql, args, getColumnMapRowMapper()));
    }

}
