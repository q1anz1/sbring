package sbringframwork.beans.factory.config;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.PropertyValues;

/**
 *  在BeanPostProcessor的基础上加了个postProcessBeforeInstantiation。
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
     * The returned bean object may be a proxy to use instead of the target bean,
     * effectively suppressing default instantiation of the target bean.
     *
     * 在 Bean 对象实例化之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * Post-process the given property values before the factory applies them
     * to the given bean. Allows for checking whether all dependencies have been
     * satisfied, for example based on a "Required" annotation on bean property setters.
     *
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    // 在 Bean 未初始化完之前就能得到引用
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }

    // 用于条件性中断初始化，或者与其他生命周期回调配合
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;
}
