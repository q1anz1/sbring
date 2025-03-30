package sbringframwork.mvc.annotation;


import org.springframework.context.annotation.Import;
import sbringframwork.mvc.support.DelegatingWebMvcConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 允许使用拦截器等额外的MVC功能注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(DelegatingWebMvcConfiguration.class)
public @interface EnableWebMvc {
}
