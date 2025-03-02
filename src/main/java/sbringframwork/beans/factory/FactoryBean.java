package sbringframwork.beans.factory;

/**
 * 工厂Bean。
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
