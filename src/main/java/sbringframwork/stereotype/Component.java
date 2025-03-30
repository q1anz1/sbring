package sbringframwork.stereotype;

import java.lang.annotation.*;

/**
 * 加入容器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@org.springframework.stereotype.Component
public @interface Component {
    String value() default "";
}
