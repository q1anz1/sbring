package sbringframwork.mvc.support;

import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import sbringframwork.mvc.WebApplicationInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Set;

/**
 * Web初始化器，MVC组件注册一切从这开始
 */
@HandlesTypes(WebApplicationInitializer.class) // Servlet启动时会把里面的东东传给onStartup的Set<Class<?>> webApplications
public class WebServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 通过SPI机制自动调用onStartup方法，并关注WebApplicationInitializer类
     *
     * Set<Class> webApplications参数包含所有被 @HandlesTypes 注解指定的类型（即 WebApplicationInitializer）的类。
     */
    @Override
    public void onStartup(Set<Class<?>> webApplications , ServletContext ctx) throws ServletException {
        if (webApplications!=null){
            final ArrayList<WebApplicationInitializer> initializers = new ArrayList<>(webApplications.size());

            for (Class<?> webApplication : webApplications) {
                // 获得用户自定义的WebApplicationInitializer，并将其添加到初始化者数组中
                if (!webApplication.isInterface() && !Modifier.isAbstract(webApplication.getModifiers())
                        && WebApplicationInitializer.class.isAssignableFrom(webApplication)){
                    try {
                        initializers.add((WebApplicationInitializer) ReflectionUtils.accessibleConstructor(webApplication).newInstance());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            //调用onStart方法
            if(!ObjectUtils.isEmpty(initializers)){
                for (WebApplicationInitializer initializer : initializers) {
                    // 利用初始化器，调用抽象类方法，初始化SpringMvc
                    initializer.onStartUp(ctx);
                }
            }
        }
    }
}
