package sbringframwork.stereotype;

import java.lang.annotation.*;

/**
 * 万恶之源。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
