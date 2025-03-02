package sbringframwork.beans.factory.support;

import sbringframwork.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition注册接口。
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
