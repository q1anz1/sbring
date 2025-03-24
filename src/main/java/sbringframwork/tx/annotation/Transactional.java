package sbringframwork.tx.annotation;

import java.lang.annotation.*;

/**
 * 开启事务。
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Transactional {
    Class<? extends Throwable>[] rollbackFor() default {};
}
