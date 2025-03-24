package sbringframwork.tx.transaction;

/**
 * 继承TransactionDefine，添加了一个rollbackOn(Throwable ex)方法。
 */
public interface TransactionAttribute extends TransactionDefinition {
    /**
     * 在进行事务回滚前用来判断对于当前发生的异常是否需要回滚。
     * */
    boolean rollbackOn(Throwable ex);
}
