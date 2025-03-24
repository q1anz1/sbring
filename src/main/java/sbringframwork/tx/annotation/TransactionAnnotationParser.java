package sbringframwork.tx.annotation;

import sbringframwork.tx.transaction.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 * 解析方法或者类上的注解得到事务的属性者。
 */
public interface TransactionAnnotationParser {

    /**
     * 解析方法或者类上的注解得到事务的属性。
     */
    TransactionAttribute parseTransactionAnnotation(AnnotatedElement element);
}
