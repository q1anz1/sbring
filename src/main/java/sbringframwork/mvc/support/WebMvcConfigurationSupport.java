package sbringframwork.mvc.support;

import org.springframework.context.annotation.Bean;
import sbringframwork.mvc.HandlerAdapter;
import sbringframwork.mvc.HandlerMapping;
import sbringframwork.mvc.adapter.RequestMappingHandlerMethodAdapter;
import sbringframwork.mvc.handler.RequestMappingHandlerMapping;
import sbringframwork.mvc.interceptor.InterceptorRegistry;
import sbringframwork.mvc.interceptor.MappedInterceptor;

import java.util.List;

/**
 * @EnableWebMVC 导入类的抽象类，用于初始化组件，允许拦截器操作
 * author: Yusiheng
 */
public abstract class WebMvcConfigurationSupport {

    /**
     * 扫描拦截器并注册到映射器中
     */
    @Bean
    public HandlerMapping handlerMapping() {
        final RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.setOrder(0);
        InterceptorRegistry registry = new InterceptorRegistry();
        // 注册中心扫描并获取拦截器
        getIntercept(registry);
        // 获取拦截器
        final List<MappedInterceptor> interceptors = registry.getInterceptors();
        //添加拦截器
        requestMappingHandlerMapping.addInterceptors(interceptors);
        return requestMappingHandlerMapping;
    }

    protected abstract void getIntercept(InterceptorRegistry registry);

    /**
     * 初始化适配器
     */
    @Bean
    public HandlerAdapter handlerMethodAdapter() {
        final RequestMappingHandlerMethodAdapter requestMappingHandlerMethodAdapter = new RequestMappingHandlerMethodAdapter();
        requestMappingHandlerMethodAdapter.setOrder(0);
        return requestMappingHandlerMethodAdapter;
    }

}
