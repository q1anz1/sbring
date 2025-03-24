package sbringframwork.mvc;

import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;

/**
 * 将 HTTP 请求映射到处理请求的处理器（Handler）。
 */
public interface HandlerMapping extends Ordered {
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
