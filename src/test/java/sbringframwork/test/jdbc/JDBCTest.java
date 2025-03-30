package sbringframwork.test.jdbc;

import org.junit.Before;
import org.junit.Test;
import sbringframwork.context.support.ClassPathXmlApplicationContext;
import sbringframwork.jdbc.support.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class JDBCTest {
    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }

    @Test
    public void executeTest() {
        // 测试建表
        jdbcTemplate.execute("INSERT INTO users (id, name) VALUE (8, 'Jaye')");
        System.out.println("插入完毕");
    }

    @Test
    public void executeUpdate() {
        jdbcTemplate.execute("UPDATE users SET name = 'Jay666' where id = 8");
    }

    @Test
    public void queryForListTest() {
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("SELECT * FROM users");
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据：", i + 1);
            Map<String, Object> objectMap = allResult.get(i);
            System.out.println(objectMap);
        }
    }

    @Test
    public void queryListWithColumnClassTypeTest() {
        List<String> allResult = jdbcTemplate.queryForList("SELECT name FROM users", String.class);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据：", i + 1);
            String username = allResult.get(i);
            System.out.println(username);
        }
    }


    @Test
    public void queryListWithColumnClassTypeWithArgTest() {
        List<String> allResult = jdbcTemplate.queryForList("SELECT name FROM users WHERE id=?", String.class, 8);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据:", i + 1);
            String username = allResult.get(i);
            System.out.println(username);
        }
    }

    @Test
    public void queryListWithArgTest() {
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("SELECT * FROM users WHERE id=?", 1);
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据:", i + 1);
            Map<String, Object> row = allResult.get(i);
            System.out.println(row);
        }
    }

    @Test
    public void queryObjectTest() {
        String username = jdbcTemplate.queryForObject("SELECT name FROM users WHERE id=8", String.class);
        System.out.println(username);
    }

    @Test
    public void queryMapTest() {
        Map<String, Object> row = jdbcTemplate.queryForMap("SELECT * FROM users where id=7");
        System.out.println(row);
    }

    @Test
    public void queryMapWithArgTest() {
        Map<String, Object> row = jdbcTemplate.queryForMap("SELECT * FROM users where id=?", 1);
        System.out.println(row);
    }
}
