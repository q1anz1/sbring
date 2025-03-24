package sbringframwork.mvc;

import org.springframework.core.Ordered;
import sbringframwork.mvc.handler.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于将 HandlerMethod（即控制器中的请求处理方法）与 HTTP 请求和响应进行绑定，并负责调用具体的请求处理方法。
 */
public interface HandlerAdapter extends Ordered {

    /**
     * 用于判断当前的 HandlerAdapter 是否支持处理给定的 HandlerMethod。
     * */
    boolean support(HandlerMethod handlerMethod);

    /**
     * 负责实际处理请求。
     * */
    void handle(HttpServletRequest req, HttpServletResponse res, HandlerMethod handler) throws Exception;

}
