package sbringframwork.context;

import sbringframwork.beans.factory.HierarchicalBeanFactory;
import sbringframwork.beans.factory.ListableBeanFactory;
import sbringframwork.context.event.ApplicationEventPublisher;
import sbringframwork.core.io.ResourceLoader;

/**
 * 应用上下文接口，本质也是个BeanFactory。
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
