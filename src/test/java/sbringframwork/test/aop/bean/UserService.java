package sbringframwork.test.aop.bean;

import lombok.Data;
import sbringframwork.beans.factory.annotation.Autowired;

/**
 *
 */
@Data
public class UserService implements IUserService {
    private String string;
    @Autowired
    private UserDAO userDAO;


    @Override
    public void useDAO() {
        userDAO.dao();
    }
}
