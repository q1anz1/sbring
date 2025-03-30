package sbringframwork.test.ioc;

import org.junit.Before;
import org.junit.Test;
import sbringframwork.context.support.ClassPathXmlApplicationContext;
import sbringframwork.test.ioc.bean.*;

/**
 *
 */
public class IoCTest {
    ClassPathXmlApplicationContext applicationContext;

    @Before
    public void init() {
        this.applicationContext = new ClassPathXmlApplicationContext("classpath:spring-ioc.xml");
    }

    @Test
    public void testBean() {
        applicationContext.registerShutdownHook();
        // 测试获取 Bean，属性和依赖注入，测试 Man 的初始化方法，销毁方法
        Man man = applicationContext.getBean("man", Man.class);
        man.say();
        System.out.println(man.getInitNumber());
        System.out.println(man.getName());

        Phone phone = man.getPhone();
        System.out.println(phone.getPhoneNumber());
        phone.call();
    }

    @Test
    public void testPostProcessor() {
        // 测试 BeanPostProcessor, man.getString() 原本为 "default"，现在被修改了
        Man man = applicationContext.getBean("man", Man.class);
        System.out.println(man.getString());
    }

    @Test
    public void testCircularDependency() {
        // 测试循环依赖，A 依赖 B， B 依赖 A
        A a = applicationContext.getBean("a", A.class);
        a.say();
        a.getB().say();

        B b = applicationContext.getBean("b", B.class);
        b.say();
        b.getA().say();
    }

    @Test
    public void testAware() {
        // a 实现了 BeanNameAware
        A a = applicationContext.getBean("a", A.class);
        // 获取 a 的 BeanName
        System.out.println(a.getBeanName());
    }

    @Test
    public void testFactoryBean() {
        // 测试 FactoryBean 的 getBean() ， 会得到 Man
        Object myFactoryBean = applicationContext.getBean("myFactoryBean");
        System.out.println(myFactoryBean.getClass().getName());
    }

    @Test
    public void testPrototype() {
        // 测试原型模式
        MyPrototypeClass myPrototypeClass1 = applicationContext.getBean("myPrototypeClass", MyPrototypeClass.class);
        MyPrototypeClass myPrototypeClass2 = applicationContext.getBean("myPrototypeClass", MyPrototypeClass.class);

        System.out.println(myPrototypeClass1.hashCode());
        System.out.println(myPrototypeClass2.hashCode());
    }

    @Test
    public void testEvent() {
        applicationContext.publishEvent(new MyEvent(applicationContext, 123123L, "消息消息消息"));
    }
}
