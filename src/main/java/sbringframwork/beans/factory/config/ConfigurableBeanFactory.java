package sbringframwork.beans.factory.config;

import sbringframwork.beans.factory.HierarchicalBeanFactory;

/**
 * 可配置的BeanFactory，...
 *
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    // TODO 补充说明
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象。
     */
    void destroySingletons();
}
