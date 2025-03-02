package sbringframwork.beans.factory.support;

import sbringframwork.beans.BeansException;
import sbringframwork.core.io.Resource;
import sbringframwork.core.io.ResourceLoader;

/**
 * Bean定义读取接口。
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
    void loadBeanDefinitions(String... location) throws BeansException;
}
