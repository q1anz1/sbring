package sbringframwork.beans.factory.aware;

import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.BeanFactory;

/**
 * 实现此接口，既能感知到所属的 BeanFactory。
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
