package sbringframwork.jdbc.support;

import sbringframwork.beans.factory.InitializingBean;

import javax.sql.DataSource;

/**
 * JdbcTemplate的父类。
 */

public abstract class JdbcAccessor implements InitializingBean {
    private DataSource dataSource;


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected DataSource obtainDataSource() {
        return getDataSource();
    }

    @Override
    public void afterPropertiesSet() {
        // TODO 为什么要检查？
        if (null == getDataSource()) {
            throw new IllegalArgumentException("Property 'datasource' is required");
        }
    }
}
