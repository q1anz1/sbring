package sbringframwork.beans.factory.support;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.ConfigurableListableBeanFactory;
import sbringframwork.beans.factory.DisposableBean;
import sbringframwork.beans.factory.config.BeanDefinition;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * （在实现上下文章节前）默认的可列表化BeanFactory，
 * 此类中的beanDefinitionMap将保存所有被注册的beanDefinition，
 * 实现了BeanDefinitionRegistry以带有注册能力，
 * 继承了AbstractAutowireCapableBeanFactory，而AbstractAutowireCapableBeanFactory继承自AbstractBeanFactory，
 * 而AbstractBeanFactory继承自DefaultSingletonBeanRegistry，让DefaultListableBeanFactory带有注册beanDefinition和
 * 在singletonObjects没有单例对象时从beanDefinitionMap中读取并创建单例并保存的能力。
 * DefaultListableBeanFactory，这是一个重要的类。
 * <p>
 * （在实现上下文章节后）
 *
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }
}
