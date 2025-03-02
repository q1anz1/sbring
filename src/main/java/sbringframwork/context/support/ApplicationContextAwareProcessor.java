package sbringframwork.context.support;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.aware.ApplicationContextAware;
import sbringframwork.beans.factory.config.BeanPostProcessor;
import sbringframwork.context.ApplicationContext;

/**
 * 包装处理器，用于感知ApplicationContextAware。
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
