package sbringframwork.boot;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import sbringframwork.boot.server.WebServer;

import java.util.Map;

/**
 * @author Yusiheng
 */

public class SpringApplication {

    /**
     * 用于启动Web程序，包含配置类注册Web程序启动等功能
     *
     * @param configClazz 配置类
     */
    public static void run(Class<?> configClazz) {
        //创建Spring容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(configClazz);
        applicationContext.refresh();
        WebServer webServer = getWebServer(applicationContext);
        webServer.start(applicationContext);
    }

    /**
     * 从程序中获取到Web，并选择合适的Web进行返回
     *
     * @param applicationContext 具体容器容器
     * @return Web的Bean实例
     */
    private static WebServer getWebServer(WebApplicationContext applicationContext) {
        Map<String, WebServer> beansOfType = applicationContext.getBeansOfType(WebServer.class);
        if (beansOfType.size() == 0) {
            throw new NullPointerException();
        }
        if (beansOfType.size() > 1) {
            throw new IllegalStateException();
        }
        return beansOfType.values().stream().findFirst().get();
    }
}