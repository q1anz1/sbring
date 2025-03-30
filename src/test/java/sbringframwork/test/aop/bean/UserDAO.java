package sbringframwork.test.aop.bean;

import sbringframwork.stereotype.Component;

/**
 *
 */
@Component
public class UserDAO {
    public void dao() {
        System.out.println("dao运行");
    }
}
