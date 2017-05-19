package main.models.dao;

import main.models.pojo.User;

/**
 * Created by sergey on 20.04.17.
 */
public interface UserDao {
    User findUserByLoginAndPassword(String login, String password);
}
