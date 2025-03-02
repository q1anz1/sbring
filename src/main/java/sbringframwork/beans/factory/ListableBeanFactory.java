package sbringframwork.beans.factory;

import sbringframwork.beans.BeansException;

import java.util.Map;

/**
 * ListableBeanFactory继承自BeanFactory，
 * 增加了通过BeanType查找Bean的接口。
 */
public interface ListableBeanFactory extends BeanFactory {
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();
}
