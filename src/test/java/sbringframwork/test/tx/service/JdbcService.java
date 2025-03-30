package sbringframwork.test.tx.service;



import sbringframwork.jdbc.support.JdbcTemplate;

import java.sql.SQLException;

public interface JdbcService {
    void saveData(JdbcTemplate jdbcTemplate) ;
}
