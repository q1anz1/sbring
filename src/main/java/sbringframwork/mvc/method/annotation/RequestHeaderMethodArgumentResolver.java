package sbringframwork.mvc.method.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.ObjectUtils;
import sbringframwork.mvc.annotation.RequestHeader;
import sbringframwork.mvc.exception.NotFoundException;
import sbringframwork.mvc.handler.HandlerMethod;
import sbringframwork.mvc.method.support.HandlerMethodArgumentResolver;
import sbringframwork.mvc.support.WebServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 获取请求头中的指定内容
 * author: Yusiheng
 */
public class RequestHeaderMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestHeader.class) && parameter.getParameterType() != Map.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, HandlerMethod handlerMethod, WebServletRequest webServletRequest, ConversionService conversionService) throws Exception {

        String name = "";
        final RequestHeader parameterAnnotation = parameter.getParameterAnnotation(RequestHeader.class);
        name = parameterAnnotation.value().equals("") ? parameter.getParameterName() : parameterAnnotation.value();
        final HttpServletRequest request = webServletRequest.getRequest();
        if (parameterAnnotation.require() && ObjectUtils.isEmpty(request.getHeader(name))) {
            throw new NotFoundException(handlerMethod.getPath() + "请求头没有携带" + name);
        }
        return conversionService.convert(request.getHeader(name), parameter.getParameterType());
    }
}
