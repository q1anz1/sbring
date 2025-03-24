package sbringframwork.mvc.interceptor;

import sbringframwork.mvc.HandlerInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 封装拦截器信息类。
 */
public class InterceptorRegistration {

    // 拦截器
    private HandlerInterceptor interceptor;

    // 用于指定拦截器应该应用的 URL 模式。换句话说，只有当请求的 URL 匹配 includePatterns 列表中的某个模式时，拦截器才会被触发。
    private List<String> includePatterns = new ArrayList<>();

    // 排除路径
    private List<String> excludePatterns = new ArrayList<>();

    public InterceptorRegistration setInterceptor(HandlerInterceptor interceptor) {
        this.interceptor = interceptor;
        return this;
    }

    public InterceptorRegistration addIncludePatterns(String... path) {
        // 不要直接赋值否则会被覆盖
        this.includePatterns.addAll(Arrays.asList(path));
        return this;
    }

    public InterceptorRegistration addExcludePatterns(String... path) {

        this.excludePatterns.addAll(Arrays.asList(path));
        return this;
    }

    public HandlerInterceptor getInterceptor() {
        return interceptor;
    }

    public List<String> getIncludePatterns() {
        return includePatterns;
    }

    public List<String> getExcludePatterns() {
        return excludePatterns;
    }
}