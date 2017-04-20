package main.services;

import main.models.pojo.User;

/**
 * Created by sergey on 20.04.17.
 */
public interface UserService {
    User auth(String login, String password);
}
