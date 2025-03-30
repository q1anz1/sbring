package sbringframwork.test.boot.intercept;

import sbringframwork.mvc.HandlerInterceptor;
import sbringframwork.mvc.handler.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class MyIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("前置处理中");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("后置处理中");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) {
        System.out.println("处理完闭");
    }
}
