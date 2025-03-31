package sbringframwork.test.tx.service.impl;


import sbringframwork.jdbc.support.JdbcTemplate;
import sbringframwork.test.tx.service.JdbcService;
import sbringframwork.tx.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Statement;

public class JdbcServiceImpl implements JdbcService {
    private Statement statement;


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveData(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("INSERT INTO users (id, name) VALUES (9, 'Jay1')");
        jdbcTemplate.execute("INSERT INTO users (id, name) VALUES ('9,'123')");
    }
}
