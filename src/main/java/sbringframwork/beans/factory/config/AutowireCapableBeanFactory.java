package sbringframwork.beans.factory.config;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.BeanFactory;

/**
 * 在BeanFactory的基础上增加了调用上下文方法。
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
