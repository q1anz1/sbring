package sbringframwork.mvc.method.support;

import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import sbringframwork.mvc.handler.HandlerMethod;
import sbringframwork.mvc.support.WebServletRequest;

/**
 * 参数解析器顶层接口
 * author: Yusiheng
 */
public interface HandlerMethodArgumentResolver {

    // 当前请求携带数据格式是否支持当前参数
    boolean supportsParameter(MethodParameter parameter);
    // 解析参数
    Object resolveArgument(MethodParameter parameter, HandlerMethod handlerMethod, WebServletRequest webServletRequest, ConversionService conversionService) throws Exception;
}
