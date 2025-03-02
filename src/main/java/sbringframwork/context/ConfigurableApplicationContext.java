package sbringframwork.context;

import sbringframwork.beans.BeansException;

/**
 * 可配置的ApplicationContext，多了个refresh。
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器。
     */
    void refresh() throws BeansException;
}
