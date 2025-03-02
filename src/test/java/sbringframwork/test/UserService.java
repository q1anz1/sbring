package sbringframwork.test;

/**
 *
 */
public class UserService {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId)+", 公司："+company+", 地点"+location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}