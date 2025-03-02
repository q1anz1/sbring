package sbringframwork.beans.factory;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.config.AutowireCapableBeanFactory;
import sbringframwork.beans.factory.config.BeanDefinition;
import sbringframwork.beans.factory.config.ConfigurableBeanFactory;

/**
 * ConfigurableListableBeanFactory扩展自一大堆接口，
 * ListableBeanFactory得到根据类型查找Bean,
 * AutowireCapableBeanFactory得到调用应用上下文方法，
 * 还继承了ConfigurableBeanFactory，它提供更多功能。
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
