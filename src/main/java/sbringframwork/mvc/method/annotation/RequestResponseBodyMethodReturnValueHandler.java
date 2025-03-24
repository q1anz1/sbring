package sbringframwork.mvc.method.annotation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.AnnotatedElementUtils;
import sbringframwork.mvc.annotation.ResponseBody;
import sbringframwork.mvc.method.support.HandlerMethodReturnValueHandler;
import sbringframwork.mvc.support.WebServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Json返回值处理器
 * author: Yusiheng
 */
public class RequestResponseBodyMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    // 避免对应实体类没有get方法
    final ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    /**
     * 判断该处理器是否支持返回值参数
     */
    @Override
    public boolean supportsReturnType(Method method) {
        return AnnotatedElementUtils.hasAnnotation(method.getDeclaringClass(), ResponseBody.class) || AnnotatedElementUtils.hasAnnotation(method, ResponseBody.class);
    }

    /**
     * 处理返回值
     */
    @Override
    public void handleReturnValue(Object returnValue, WebServletRequest webServletRequest) throws Exception {
        final HttpServletResponse response = webServletRequest.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(returnValue));
        response.getWriter().flush();
    }
}