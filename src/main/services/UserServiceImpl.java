package main.services;

import main.models.dao.UserDao;
import main.models.dao.UserDaoImpl;
import main.models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public User auth(String login, String password) {
        User user = userDao.findUserByLoginAndPassword(login, password);
        if (user == null) {
            LOGGER.debug("User with these credentials not found");
            return null;
        }

        if (user.isBlocked()) {
            LOGGER.debug("User " + login + " blocked");
            return null;
        }

        LOGGER.debug("user: " + user.getLogin());

        return user;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
