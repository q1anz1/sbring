package sbringframwork.beans.factory;

import sbringframwork.beans.BeansException;

/**
 * Defines a factory which can return an Object instance
 */
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
