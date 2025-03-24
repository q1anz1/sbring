package sbringframwork.mvc;

import sbringframwork.mvc.handler.HandlerMethod;
import sbringframwork.mvc.interceptor.MappedInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * HandlerExecutionChain是Spring MVC框架中用于处理请求的执行链，
 * 它包含了处理该请求的处理器（Handler）和可能对该请求实施拦截的拦截器（HandlerInterceptor）。
 * <p>
 * 请求接收：DispatcherServlet接收到客户端的请求。
 * 处理器查找：DispatcherServlet通过HandlerMapping组件找到与请求匹配的处理器，并将其封装在HandlerExecutionChain中。
 * 拦截器执行（预处理）：在调用处理器之前，HandlerExecutionChain会按照配置的顺序执行所有拦截器的preHandle方法。如果任何一个拦截器的preHandle方法返回false，则请求处理流程将中断，不会继续执行后续的拦截器或处理器。
 * 处理器调用：如果所有拦截器的preHandle方法都返回true，则HandlerExecutionChain将调用处理器的方法来处理请求。
 * 拦截器执行（后处理）：在处理器处理完请求后，HandlerExecutionChain会按照相反的顺序执行所有拦截器的postHandle方法。
 * 完成处理：在所有拦截器的postHandle方法执行完毕后，HandlerExecutionChain会执行拦截器的afterCompletion方法（如果配置了的话），以完成请求的处理流程。
 * <p>
 * 拦截器配置：开发者可以通过实现WebMvcConfigurer接口并重写addInterceptors方法来配置拦截器。在配置拦截器时，可以指定拦截器的路径匹配规则，以便将拦截器应用于特定的请求路径。
 * 处理器映射：HandlerMapping组件负责将请求映射到对应的处理器。
 * Spring MVC提供了多种HandlerMapping实现，例如RequestMappingHandlerMapping，它可以根据@RequestMapping注解将请求映射到对应的处理器方法。
 */
public class HandlerExecutionChain {
    private HandlerMethod handlerMethod;

    private List<HandlerInterceptor> handlerInterceptors = new ArrayList<>();

    public HandlerExecutionChain(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    public List<HandlerInterceptor> getHandlerInterceptors() {
        return handlerInterceptors;
    }

    public void setHandlerInterceptors(List<HandlerInterceptor> handlerInterceptors) {
        // 检查Interceptor所拦截的HandlerMethod是否是存在的
        for (HandlerInterceptor interceptor : handlerInterceptors) {
            if (interceptor instanceof MappedInterceptor) {
                if (((MappedInterceptor) interceptor).match(handlerMethod.getPath())) {
                    this.handlerInterceptors.add(interceptor);
                }
            } else {
                this.handlerInterceptors.add(interceptor);
            }
        }
    }

    // 多个拦截器执行，一旦有一个拦截器返回false，整个链路就可以崩掉
    public boolean applyPreInterceptor(HttpServletRequest req, HttpServletResponse resp) {
        for (HandlerInterceptor handlerInterceptor : this.handlerInterceptors) {
            if (!handlerInterceptor.preHandle(req, resp)) {
                return false;
            }
        }
        return true;
    }

    public void applyPostInterceptor(HttpServletRequest req, HttpServletResponse resp) {
        for (HandlerInterceptor handlerInterceptor : this.handlerInterceptors) {
            handlerInterceptor.postHandle(req, resp);
        }
    }

    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, HandlerMethod handlerMethod, Exception ex) {
        for (HandlerInterceptor handlerInterceptor : this.handlerInterceptors) {
            handlerInterceptor.afterCompletion(req, resp, handlerMethod, ex);
        }
    }
}
