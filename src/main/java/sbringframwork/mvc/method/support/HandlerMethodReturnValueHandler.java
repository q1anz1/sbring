package sbringframwork.mvc.method.support;

import sbringframwork.mvc.support.WebServletRequest;

import java.lang.reflect.Method;

/**
 * 返回值处理器顶层接口
 * author: Yusiheng
 */
public interface HandlerMethodReturnValueHandler {

    // 当前method 是否支持
    boolean supportsReturnType(Method method);

    // 执行
    void handleReturnValue(Object returnValue, WebServletRequest webServletRequest) throws Exception;
}

