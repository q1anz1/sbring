package sbringframwork.beans.factory;

/**
 * HierarchicalBeanFactory 提供父容器的访问功能.
 * 至于父容器的设置,需要找ConfigurableBeanFactory的setParentBeanFactory(接口把设置跟获取给拆开了!)。
 */
public interface HierarchicalBeanFactory extends BeanFactory {
}
