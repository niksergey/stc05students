package main.models.dao;

import main.models.pojo.StudyGroup;
import main.utils.DatabaseManager;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudyGroupDaoImpl implements StudyGroupDao {

    private StudyGroup createEntity(ResultSet result) {
        StudyGroup group = null;
        try {
            group = new StudyGroup(
                    result.getInt("id"),
                    result.getString("name"));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return group;
    }

    public List<StudyGroup> getAllGroups() {
        String query = "SELECT * FROM study_group;";
        List<StudyGroup> groups = new ArrayList<>(20);

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next())
                    groups.add(createEntity(result));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return groups;
    }

    public StudyGroup getById(int id) {
        String query = "SELECT * FROM study_group WHERE id=?;";
        StudyGroup group = null;
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    group = createEntity(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public StudyGroup getByName(String name) {
        StudyGroup group = null;
        String query = "SELECT * FROM study_group WHERE name=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    group = createEntity(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public boolean insertStudyGroup(StudyGroup group) {
        String query = "INSERT INTO study_group (id, name)" +
                " VALUES (?, ?);";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, group.getId());
            statement.setString(2, group.getName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudyGroup(StudyGroup group) {
        String query = "UPDATE study_group SET name=? WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudyGroup(int id) {
        String query = "DELETE FROM study_group WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
