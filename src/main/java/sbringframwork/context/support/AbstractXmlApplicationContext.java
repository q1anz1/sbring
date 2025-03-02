package sbringframwork.context.support;

import sbringframwork.beans.factory.support.DefaultListableBeanFactory;
import sbringframwork.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 增加了上下文中对配置信息的加载。
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
