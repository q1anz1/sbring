package sbringframwork.test.ioc.bean;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.config.BeanPostProcessor;

/**
 *
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Man man) {
            man.setString("这个 String 被 MyBeanPostProcessor 修改了。");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
