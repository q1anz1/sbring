package sbringframwork.beans.factory.aware;

import sbringframwork.beans.BeansException;
import sbringframwork.context.ApplicationContext;

/**
 * 实现此接口，既能感知到所属的 ApplicationContext。
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
