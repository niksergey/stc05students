package main.models.dao;

import main.models.pojo.StudyGroup;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudyGroupDao implements StudyGroupDaoInterface {

    private List<StudyGroup> constructFromResult(ResultSet result) {
        List<StudyGroup> groups = new ArrayList<StudyGroup>(16);
        try {
            while (result.next()) {
                StudyGroup group = new StudyGroup(
                        result.getInt("id"),
                        result.getString("name"));
                groups.add(group);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return groups;
    }

    public List<StudyGroup> getAllGroups() {
        List<StudyGroup> groups = null;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();

            String query = "SELECT * FROM study_group;";
            Statement statement = conn.createStatement();
            groups = constructFromResult(statement.executeQuery(query));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return groups;
    }

    public List<StudyGroup> getById(int id) {
        List<StudyGroup> groups = null;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM study_group WHERE id=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            groups = constructFromResult(statement.executeQuery(query));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return groups;
    }

    public List<StudyGroup> getByName(String name) {
        List<StudyGroup> groups = null;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM study_group WHERE name=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            groups = constructFromResult(statement.executeQuery(query));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return groups;
    }

    public boolean insertStudyGroup(StudyGroup group) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "INSERT INTO study_group (id, name)" +
                    " VALUES (?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, group.getId());
            preparedStatement.setString(2, group.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudyGroup(StudyGroup group) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "UPDATE study_group SET name=? WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudyGroup(StudyGroup group) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "DELETE FROM study_group WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, group.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
