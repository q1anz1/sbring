package sbringframwork.mvc.interceptor;

import org.springframework.util.AntPathMatcher;
import sbringframwork.mvc.HandlerInterceptor;
import sbringframwork.mvc.handler.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 *
 * 用于配置和管理拦截器，它实现了拦截器与请求路径的映射关系。
 *
 * 当请求到达Spring MVC的DispatcherServlet时，它会查找匹配的处理器（Handler）。在找到处理器之前，DispatcherServlet会检查是否存在与请求路径匹配的MappedInterceptor。
 * 如果存在，它会按照配置的顺序执行这些拦截器的preHandle方法。如果所有拦截器的preHandle方法都返回true，
 * 则请求会继续被处理器处理；如果任何一个拦截器的preHandle方法返回false，则请求会被拦截，不会继续执行后续的拦截器或处理器。
 * 在处理器处理完请求后，DispatcherServlet会按照相反的顺序执行拦截器的postHandle方法。
 * 最后，在所有拦截器的postHandle方法执行完毕后，会执行拦截器的afterCompletion方法。
 */
public class MappedInterceptor implements HandlerInterceptor {

    // 拦截器
    private HandlerInterceptor interceptor;

    // 要拦截的路径
    private List<String> includePatterns = new ArrayList<>();

    // 被排除的路径
    private List<String> excludePatterns = new ArrayList<>();

    // AntPathMatcher 是 Spring 框架中用于路径匹配的一个工具类，它基于 Ant 风格的路径匹配规则，能够判断两个路径字符串是否匹配。
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public MappedInterceptor(InterceptorRegistration interceptorRegistration) {
        this.interceptor = interceptorRegistration.getInterceptor();
        this.includePatterns = interceptorRegistration.getIncludePatterns();
        this.excludePatterns = interceptorRegistration.getExcludePatterns();
    }

    // 路径匹配
    public boolean match(String path) {
        for (String excludePattern : this.excludePatterns) {
            if (antPathMatcher.match(excludePattern, path)) {
                return false;
            }
        }
        for (String includePattern : this.includePatterns) {
            if (antPathMatcher.match(includePattern, path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
        return interceptor.preHandle(request, response);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response) {
        interceptor.postHandle(request, response);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) {
        interceptor.afterCompletion(request, response, handler, ex);
    }
}
