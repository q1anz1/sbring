package sbringframwork.mvc.method.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import sbringframwork.mvc.handler.HandlerMethod;
import sbringframwork.mvc.method.support.HandlerMethodArgumentResolver;
import sbringframwork.mvc.support.WebServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 无需转换
 * author: Yusiheng
 */
public class ServletRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == HttpServletRequest.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, HandlerMethod handlerMethod, WebServletRequest webServletRequest, ConversionService conversionService) throws Exception {
        return webServletRequest.getRequest();
    }
}
