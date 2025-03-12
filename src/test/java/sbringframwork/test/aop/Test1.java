package sbringframwork.test.aop;

import org.junit.Test;
import sbringframwork.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class Test1 {
    @Test
    public void test1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService bean = (UserService) applicationContext.getBean("userService");
        System.out.println(bean.queryUserInfo());
    }
}
