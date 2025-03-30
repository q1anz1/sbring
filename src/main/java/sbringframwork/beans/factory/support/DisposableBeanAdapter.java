package sbringframwork.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.DisposableBean;
import sbringframwork.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 销毁方法适配器，
 * 可能你会想这里怎么有一个适配器的类呢，因为销毁方法有两种甚至多种方式，目前有实现接口 DisposableBean、配置信息 destroy-method，两种方式。
 * 而这两种方式的销毁动作是由 AbstractApplicationContext 在注册虚拟机钩子后看，虚拟机关闭前执行的操作动作。
 * 那么在销毁执行时不太希望还得关注都销毁那些类型的方法，它的使用上更希望是有一个统一的接口进行销毁，所以这里就新增了适配类，做统一处理。
 * 这个 DisposableBeanAdapter 自己也是个 DisposableBean。
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 看 this.bean 是否实现接口 DisposableBean，有就销毁
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 2. 看配置信息（Spring.xml）中Bean有没有 destroy-method，有的话也要调用
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod;
            try {
                destroyMethod= bean.getClass().getMethod(destroyMethodName);
            } catch (NoSuchMethodException e) {
                throw new BeansException("在Bean '" + beanName + "' 中找不到名为 '" + destroyMethodName + "' 的方法。");
            }
            destroyMethod.invoke(bean);
        }
    }
}
