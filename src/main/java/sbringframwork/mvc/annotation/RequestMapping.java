package sbringframwork.mvc.annotation;

import java.lang.annotation.*;

/**
 *
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
public @interface RequestMapping {
    String value() default "";

    RequestMethod[] requestMethod() default {};
}
