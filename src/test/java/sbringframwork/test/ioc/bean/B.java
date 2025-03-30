package sbringframwork.test.ioc.bean;

import lombok.Data;

/**
 *
 */
@Data
public class B {
    private A a;

    public void say() {
        System.out.println("I am B");
    }
}
