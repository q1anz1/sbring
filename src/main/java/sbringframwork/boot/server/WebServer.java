package sbringframwork.boot.server;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author Yusiheng
 */

public interface WebServer {

    void start(AnnotationConfigWebApplicationContext applicationContext);

}

