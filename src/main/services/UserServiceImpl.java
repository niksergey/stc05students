package main.services;

import main.models.dao.UserDao;
import main.models.dao.UserDaoImpl;
import main.models.pojo.User;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by sergey on 20.04.17.
 */
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private  static UserDao userDao = new UserDaoImpl();

    public User auth(String login, String password) {
        User user = userDao.findUserByLoginAndPassword(login, password);
        LOGGER.debug("user: " + user);

        if (user != null && user.isBlock()) {
            return null;
        }
        LOGGER.debug("user not blocked");

        return user;
    }
}
