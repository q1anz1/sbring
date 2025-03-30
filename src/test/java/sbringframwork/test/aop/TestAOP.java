package sbringframwork.test.aop;

import org.junit.Before;
import org.junit.Test;
import sbringframwork.beans.factory.annotation.Autowired;
import sbringframwork.context.support.ClassPathXmlApplicationContext;
import sbringframwork.stereotype.Component;
import sbringframwork.test.aop.bean.IUserService;

/**
 *
 */
public class TestAOP {
    ClassPathXmlApplicationContext applicationContext;

    @Autowired
    private IUserService userService;

    @Before
    public void init() {
        this.applicationContext = new ClassPathXmlApplicationContext("classpath:spring-aop.xml");
    }

    @Test
    public void test_aop() {
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        // 测试 AOP，测试 Bean 中的属性有没有正常获取
        System.out.println("getString结果：" + userService.getString());
    }

    @Test
    public void testAutoWired() {
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        userService.useDAO();
    }
}
