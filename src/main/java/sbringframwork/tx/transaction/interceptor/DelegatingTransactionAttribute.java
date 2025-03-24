package sbringframwork.tx.transaction.interceptor;

import sbringframwork.tx.transaction.TransactionAttribute;
import sbringframwork.tx.transaction.support.DelegatingTransactionDefinition;

import java.io.Serializable;

/**
 *
 */
public abstract class DelegatingTransactionAttribute extends DelegatingTransactionDefinition implements TransactionAttribute, Serializable {

    private final TransactionAttribute targetAttribute;


    public DelegatingTransactionAttribute(TransactionAttribute targetAttribute) {
        super(targetAttribute);
        this.targetAttribute = targetAttribute;
    }


    @Override
    public boolean rollbackOn(Throwable ex) {
        return this.targetAttribute.rollbackOn(ex);
    }
}
