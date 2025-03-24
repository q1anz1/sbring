package sbringframwork.tx.transaction;

/**
 * 定义了获取事务状态、事务提交、事务回滚等方法。
 */
public interface PlatformTransactionManager {
    TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;
}
