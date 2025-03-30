package sbringframwork.test.boot.config;

import sbringframwork.mvc.annotation.EnableWebMvc;
import sbringframwork.mvc.interceptor.InterceptorRegistry;
import sbringframwork.mvc.support.WebMvcConfigurer;
import sbringframwork.stereotype.Component;
import sbringframwork.test.boot.intercept.MyIntercept;

/**
 *
 */
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addIntercept(InterceptorRegistry registry){
        registry.addInterceptor(new MyIntercept()).addIncludePatterns("/hello");
    }
}
