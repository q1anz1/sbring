package sbringframwork.mvc;

import sbringframwork.mvc.handler.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器（Interceptor）是一种在请求处理的不同阶段插入自定义逻辑的机制，通常用于实现诸如权限验证、日志记录、性能监控、请求修改或响应处理等功能。
 */
public interface HandlerInterceptor {

    /**
     * 在控制器方法被调用之前执行。通常用于权限验证、请求预处理等。
     * 返回 true：请求继续，流程进入下一个拦截器（如果有）或最终到达控制器。
     * 返回 false：请求被中断，后续的拦截器和控制器方法将不会被调用。
     * */
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    /**
     * 请求处理之后，视图渲染之前。
     * 作用：在控制器方法调用完成并且返回视图之前执行。通常用于修改或记录响应数据。
     * */
    default void postHandle(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 请求处理完成后，包括视图渲染之后。
     * 作用：在整个请求处理过程完成后执行，通常用于清理资源、记录日志或处理异常。
     * Exception ex：请求处理过程中抛出的异常（如果有）。
     * */
    default void afterCompletion(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) {

    }
}
