package sbringframwork.test.beans;

import sbringframwork.beans.factory.DisposableBean;
import sbringframwork.beans.factory.InitializingBean;

/**
 *
 */
public class UserService implements InitializingBean, DisposableBean {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    public String queryUserInfo() {
        return "结果123345";
    }
}