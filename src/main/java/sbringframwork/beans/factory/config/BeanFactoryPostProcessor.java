package sbringframwork.beans.factory.config;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.ConfigurableListableBeanFactory;

/**
 * BeanFactory后处理接口，
 * 提供在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制。
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制。
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
