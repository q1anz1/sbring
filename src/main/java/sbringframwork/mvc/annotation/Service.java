package sbringframwork.mvc.annotation;

import java.lang.annotation.*;

/**
 *
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String value() default "";
}
