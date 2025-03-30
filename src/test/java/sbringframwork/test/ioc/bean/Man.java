package sbringframwork.test.ioc.bean;

import lombok.Data;
import sbringframwork.beans.factory.DisposableBean;
import sbringframwork.beans.factory.InitializingBean;

/**
 *
 */
@Data
public class Man implements InitializingBean, DisposableBean {
    private String name;

    private Phone phone;

    private Integer initNumber;

    private String string = "default";


    public void say() {
        System.out.println("What can I say");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行 Man 销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initNumber = 123;
    }
}
