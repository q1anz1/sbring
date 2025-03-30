package sbringframwork.test.ioc.bean;

import lombok.Data;
import sbringframwork.beans.factory.aware.BeanNameAware;

/**
 *
 */
@Data
public class A implements BeanNameAware {
    private B b;

    private String beanName;

    public void say() {
        System.out.println("I am A");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }


}
