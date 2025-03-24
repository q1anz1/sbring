package sbringframwork.tx.transaction;

import java.io.Flushable;
import java.io.IOException;

/**
 * 对事务的状态进行描述，定义了Savepoint、是否是新事务等信息。通过TransactionDefinition中的事务属性来创建一个TransactionStatus。
 */
public interface TransactionStatus extends SavepointManager, Flushable {
    /**
     * 是否开启新的事务
     */
    boolean isNewTransaction();

    boolean hasSavepoint();

    void setRollbackOnly();

    boolean isRollbackOnly();

    @Override
    void flush() throws IOException;

    boolean isCompleted();
}
