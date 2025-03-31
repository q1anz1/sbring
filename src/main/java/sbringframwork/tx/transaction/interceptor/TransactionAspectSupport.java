package sbringframwork.tx.transaction.interceptor;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.thread.threadlocal.NamedThreadLocal;
import sbringframwork.beans.BeansException;
import sbringframwork.beans.factory.BeanFactory;
import sbringframwork.beans.factory.InitializingBean;
import sbringframwork.beans.factory.aware.BeanFactoryAware;
import sbringframwork.core.util.ClassUtils;
import sbringframwork.tx.transaction.PlatformTransactionManager;
import sbringframwork.tx.transaction.TransactionAttribute;
import sbringframwork.tx.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 *
 */
public abstract class TransactionAspectSupport implements BeanFactoryAware, InitializingBean {
    private static final ThreadLocal<TransactionInfo> transactionInfoHolder = new NamedThreadLocal<>("Current aspect-driven transaction");

    private BeanFactory beanFactory;

    private TransactionAttributeSource transactionAttributeSource;

    private PlatformTransactionManager transactionManager;


    protected Object invokeWithinTransaction(Method method, Class<?> targetClass, InvocationCallback invocation)throws Throwable {
        // 获得 this.TransactionAttributeSource 在第一步初始化中的那个
        TransactionAttributeSource tas = getTransactionAttributeSource();
        // 从 tas 获得 TransactionAttribute
        TransactionAttribute txAttr = tas.getTransactionAttribute(method, targetClass);
        // 获得 this.transactionManager，也是第一步初始化的那个
        PlatformTransactionManager tm = determineTransactionManager();
        // 获取目标方法的唯一标识
        String joinpointIdentification = methodIdentification(method, targetClass);
        // 一个内部类，封装入参
        TransactionInfo txInfo = createTransactionIfNecessary(tm, txAttr, joinpointIdentification);

        // 存储目标方法返回值
        Object retVal;
        try {
            // 执行目标方法
            retVal = invocation.proceedWithInvocation();
        } catch (Exception e) {
            // 抛异常就回滚
            completeTransactionAfterThrowing(txInfo, e);
            throw e;
        } finally {
            // 清理 TransactionInfo
            cleanupTransactionInfo(txInfo);
        }
        // 提交事务
        commitTransactionAfterReturning(txInfo);

        return retVal;
    }

    public TransactionAttributeSource getTransactionAttributeSource() {
        return transactionAttributeSource;
    }

    public void setTransactionAttributeSource(TransactionAttributeSource transactionAttributeSource) {
        this.transactionAttributeSource = transactionAttributeSource;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 当前使用DataSourceTransactionManager
     */
    protected PlatformTransactionManager determineTransactionManager() {
        return getTransactionManager();
    }

    /**
     * 获取目标方法的唯一标识
     */
    private String methodIdentification(Method method, Class<?> targetClass) {
        return ClassUtils.getQualifiedMethodName(method, targetClass);
    }

    protected TransactionInfo createTransactionIfNecessary(PlatformTransactionManager tm, TransactionAttribute txAttr, String joinpointIdentification){
        if (txAttr != null && txAttr.getName() == null) {
            txAttr = new DelegatingTransactionAttribute(txAttr) {
                @Override
                public String getName() {
                    return joinpointIdentification;
                }
            };
        }

        TransactionStatus status = null;
        if (txAttr != null) {
            if (tm != null) {
                status = tm.getTransaction(txAttr);
            }
        }
        return prepareTransactionInfo(tm, txAttr, joinpointIdentification, status);
    }

    protected TransactionInfo prepareTransactionInfo(PlatformTransactionManager tm, TransactionAttribute txAttr, String joinpointIdentification, TransactionStatus status) {
        TransactionInfo txInfo = new TransactionInfo(tm, txAttr, joinpointIdentification);
        if (txAttr != null) {
            txInfo.newTransactionStatus(status);
        }
        txInfo.bindToThread();
        return txInfo;
    }

    protected void completeTransactionAfterThrowing(TransactionInfo txInfo, Throwable ex) {
        if (null != txInfo && null != txInfo.getTransactionStatus()) {
            // && txInfo.transactionAttribute.rollbackOn(ex)
            if (txInfo.transactionAttribute != null ) {
                // 异常类型是需要回滚的类型
                try {
                    // 回滚
                    txInfo.getTransactionManager().rollback(txInfo.getTransactionStatus());
                    System.out.println("回滚完毕");
                } catch (RuntimeException | Error ex2) {
                    throw ex2;
                }
            } else {
                // 异常类型不是需要回滚的类型，直接提交
                try {
                    txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
                } catch (Exception ex2) {
                    throw ex2;
                }
            }
        }
    }

    protected void cleanupTransactionInfo(TransactionInfo txInfo) {
        if (null != txInfo) {
            txInfo.restoreThreadLocalStatus();
        }
    }

    protected void commitTransactionAfterReturning(TransactionInfo txInfo) {
        if (null != txInfo && null != txInfo.getTransactionStatus()) {
            txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
        }
    }



    protected interface InvocationCallback {
        Object proceedWithInvocation() throws Throwable;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    protected final class TransactionInfo {

        private final PlatformTransactionManager transactionManager;
        private final TransactionAttribute transactionAttribute;
        private final String joinpointIdentification;
        private TransactionStatus transactionStatus;
        private TransactionInfo oldTransactionInfo;

        public TransactionInfo(PlatformTransactionManager transactionManager,
                               TransactionAttribute transactionAttribute, String joinpointIdentification) {
            this.transactionManager = transactionManager;
            this.transactionAttribute = transactionAttribute;
            this.joinpointIdentification = joinpointIdentification;
        }

        public PlatformTransactionManager getTransactionManager() {
            Assert.state(this.transactionManager != null, "No PlatformTransactionManager set");
            return transactionManager;
        }

        public String getJoinpointIdentification() {
            return joinpointIdentification;
        }

        public TransactionAttribute getTransactionAttribute() {
            return transactionAttribute;
        }

        public void newTransactionStatus(TransactionStatus status) {
            this.transactionStatus = status;
        }

        public TransactionStatus getTransactionStatus() {
            return transactionStatus;
        }

        public boolean hasTransaction() {
            return null != this.transactionStatus;
        }

        private void bindToThread() {
            this.oldTransactionInfo = transactionInfoHolder.get();
            transactionInfoHolder.set(this);
        }

        private void restoreThreadLocalStatus() {
            transactionInfoHolder.set(this.oldTransactionInfo);
        }
    }
}
