package main.models.dao;

import main.models.pojo.User;
import main.utils.DatabaseManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
    private final static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private List<User> constructFromResult(ResultSet result) {
        List<User> groups = new ArrayList<User>(16);
        try {
            while (result.next()) {
                User user = new User(result.getLong("id"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getBoolean("is_blocked"));
                groups.add(user);
            }
            result.close();
            result = null;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return groups;
    }

    public User findUserByLoginAndPassword(String login, String password) {
        List<User> students = null;
        User stud = null;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM users WHERE login=? AND password=?;";
            statement = conn.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, password);
            students = constructFromResult(statement.executeQuery());
            stud = students.iterator().next();
            LOGGER.debug("user " + stud.getLogin());
            statement.close();
            statement = null;
            conn.close();
            conn = null;
        } catch (SQLException e ) {
            LOGGER.debug("SqlException in UserDao.findUserByLoginAndPassword ", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return stud;
    }

    private User createEntity(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getLong("id"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_blocked"));
        return user;
    }
}
