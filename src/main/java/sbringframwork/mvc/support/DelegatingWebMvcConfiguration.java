package sbringframwork.mvc.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sbringframwork.mvc.interceptor.InterceptorRegistry;


import java.util.List;

/**
 * author: Yusiheng
 */
@Component
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport{

    private final WebMvcComposite webMvcComposite = new WebMvcComposite();

    /**
     * 通过自动装配，来获取到用户自定义的配置类
     */
    @Autowired(required = false)
    public void setWebMvcComposite(List<WebMvcConfigurer> webMvcConfigurers){
        webMvcComposite.addWebMvcConfigurers(webMvcConfigurers);
    }


    /**
     * 注册中心扫描并获取拦截器
     */
    @Override
    protected void getIntercept(InterceptorRegistry registry) {
        webMvcComposite.addIntercept(registry);
    }
}