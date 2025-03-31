package sbringframwork.tx.annotation;


import sbringframwork.core.annotation.AnnotatedElementUtils;
import sbringframwork.core.annotation.AnnotationAttributes;
import sbringframwork.tx.transaction.RuleBasedTransactionAttribute;
import sbringframwork.tx.transaction.TransactionAttribute;
import sbringframwork.tx.transaction.interceptor.RollbackRuleAttribute;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于实现解析Transaction注解，获取业务中设置的相关属性。
 */
public class SpringTransactionAnnotationParser implements TransactionAnnotationParser, Serializable {

    @Override
    public TransactionAttribute parseTransactionAnnotation(AnnotatedElement annotatedElement) {
        // 自制
        Transactional annotation = annotatedElement.getAnnotation(Transactional.class);
        if (annotation != null) {
            Class<? extends Throwable>[] classes = annotation.rollbackFor();
            TransactionAttribute transactionAttribute = parseTransactionAnnotation2(classes);
            return transactionAttribute;
        } else {
            return null;
        }

        // TODO 怎么找不到
/*        AnnotationAttributes annotationAttributes = AnnotatedElementUtils.findMergedAnnotationAttributes(annotatedElement, Transactional.class, false, false);
        if (null != annotationAttributes) {
            return parseTransactionAnnotation2(annotationAttributes);
        } else {
            return null;
        }*/
    }

    protected TransactionAttribute parseTransactionAnnotation2(Class<? extends Throwable>[] classes) {
        RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();
        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        for (Class<?> rbRule : classes) {
            rollbackRules.add(new RollbackRuleAttribute(rbRule));
        }
        rbta.setRollbackRules(rollbackRules);
        return rbta;
    }


    protected TransactionAttribute parseTransactionAnnotation(AnnotationAttributes attributes) {
        RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();
        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        for (Class<?> rbRule : attributes.getClassArray("rollbackFor")) {
            rollbackRules.add(new RollbackRuleAttribute(rbRule));
        }
        rbta.setRollbackRules(rollbackRules);
        return rbta;
    }
}
