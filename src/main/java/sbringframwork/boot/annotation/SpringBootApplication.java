package sbringframwork.boot.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import sbringframwork.boot.server.WebServerAutoConfiguration;
import sbringframwork.mvc.annotation.EnableWebMvc;

import java.lang.annotation.*;

/**
 * boot程序核心注解包括扫描包和引入组合器，组合器没有实现引入了web的自动配置类用于启动程序
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ComponentScan()
@Import(WebServerAutoConfiguration.class)
@EnableWebMvc
public @interface SpringBootApplication {
}

