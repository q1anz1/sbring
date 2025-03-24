package sbringframwork.tx.transaction.interceptor;

import sbringframwork.tx.transaction.TransactionAttribute;

import java.lang.reflect.Method;

/**
 * 该接口可以认为是TransactionAttribute的包装接口，该接口中就一个获取TransactionAttribute的方法。
 */
public interface TransactionAttributeSource {
    /**
     * 获取TransactionAttribute。
     * */
    TransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass);
}
