package sbringframwork.beans.factory.aware;

/**
 * 实现此接口，既能感知到所属的 BeanFactory。
 */
public interface BeanNameAware extends Aware {
    void setBeanName(String name);
}
