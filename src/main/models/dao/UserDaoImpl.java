package main.models.dao;

import main.models.pojo.User;
import main.utils.DatabaseManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private final static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private User createEntity(ResultSet result) {
        User user = null;
        try {
            user = new User(result.getLong("id"),
                    result.getString("login"),
                    result.getString("password"),
                    result.getBoolean("is_blocked"));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByLoginAndPassword(String login, String password) {
        User user = null;

        String query = "SELECT * FROM users WHERE login=? AND password=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query);
             ) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        user = createEntity(result);
                    }
                }
        } catch (SQLException e ) {
            LOGGER.debug("SqlException in findUserByLoginAndPassword ", e);
        }
        return user;
    }
}
