package sbringframwork.test.aop;

import org.junit.Test;
import sbringframwork.aop.AdvisedSupport;
import sbringframwork.aop.TargetSource;
import sbringframwork.aop.aspectj.AspectJExpressionPointcut;
import sbringframwork.aop.framework.JdkDynamicAopProxy;
import sbringframwork.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class Test123 {
    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
