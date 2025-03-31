package sbringframwork.test.tx.service;



import sbringframwork.jdbc.support.JdbcTemplate;
import sbringframwork.tx.annotation.Transactional;

import java.sql.SQLException;

public interface JdbcService {
    @Transactional(rollbackFor = Throwable.class)
    void saveData(JdbcTemplate jdbcTemplate) ;
}
