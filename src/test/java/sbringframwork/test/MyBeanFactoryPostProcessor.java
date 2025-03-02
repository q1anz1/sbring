package sbringframwork.test;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.PropertyValue;
import sbringframwork.beans.PropertyValues;
import sbringframwork.beans.factory.ConfigurableListableBeanFactory;
import sbringframwork.beans.factory.config.BeanDefinition;
import sbringframwork.beans.factory.config.BeanFactoryPostProcessor;

/**
 *
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
