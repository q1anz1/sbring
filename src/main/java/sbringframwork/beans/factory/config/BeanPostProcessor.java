package sbringframwork.beans.factory.config;

import sbringframwork.beans.BeansException;

/**
 * Bean后处理接口，
 * 提供在 Bean 对象执行初始化方法之前后，执行方法的机制。
 */
public interface BeanPostProcessor {
    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法。
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法。
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
