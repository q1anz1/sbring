package sbringframwork.test.ioc.bean;

import lombok.Data;

/**
 *
 */
@Data
public class Phone {
    private String phoneNumber;

    public void call() {
        System.out.println("phone call");
    }
}
