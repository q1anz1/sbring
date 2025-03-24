package sbringframwork.tx.transaction;

import sbringframwork.tx.transaction.interceptor.RollbackRuleAttribute;

import java.io.Serializable;
import java.util.List;

/**
 * 增加了对RollbackRule规则们进行单独的设置的DefaultTransactionAttribute。TransactionDefinition的子孙。
 */
public class RuleBasedTransactionAttribute extends DefaultTransactionAttribute implements Serializable {

    private List<RollbackRuleAttribute> rollbackRules;

    public RuleBasedTransactionAttribute() {
        super();
    }

    public void setRollbackRules(List<RollbackRuleAttribute> rollbackRules) {
        this.rollbackRules = rollbackRules;
    }
}
