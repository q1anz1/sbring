package sbringframwork.test.ioc.bean;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.ConfigurableListableBeanFactory;
import sbringframwork.beans.factory.config.BeanFactoryPostProcessor;

/**
 *
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("MyBeanFactoryPostProcessor 的 postProcessBeanFactory 执行。");
    }
}
