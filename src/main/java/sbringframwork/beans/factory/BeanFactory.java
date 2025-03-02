package sbringframwork.beans.factory;

import sbringframwork.beans.BeansException;

/**
 * BeanFactory。
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
}
