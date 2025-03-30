package sbringframwork.tx.transaction.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import sbringframwork.tx.transaction.PlatformTransactionManager;

import java.io.Serializable;

/**
 * 声明式事务管理的核心拦截器类。
 */
public class TransactionInterceptor extends TransactionAspectSupport implements MethodInterceptor, Serializable {

    public TransactionInterceptor(PlatformTransactionManager ptm, TransactionAttributeSource transactionAttributeSource) {
        setTransactionManager(ptm);
        setTransactionAttributeSource(transactionAttributeSource);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return invokeWithinTransaction(invocation.getMethod(),
                invocation.getThis().getClass(), invocation::proceed);
    }
}
