package sbringframwork.tx.transaction.interceptor;

import java.io.Serializable;

/**
 * 装回滚的规则。
 */
public class RollbackRuleAttribute implements Serializable {

    private final String exceptionName;

    public RollbackRuleAttribute(Class<?> clazz) {
        this.exceptionName = clazz.getName();
    }
}
