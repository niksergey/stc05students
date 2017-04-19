package main.models.dao;

import main.models.pojo.StudyGroup;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudyGroupDao implements StudyGroupDaoInterface {
    public List<StudyGroup> getAllGroups() {
        List<StudyGroup> groups = new ArrayList<StudyGroup>(30);
        ResultSet result;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();

            String query = "SELECT * FROM study_group;";
            Statement statement = conn.createStatement();
            result = statement.executeQuery(query);
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

    public List<StudyGroup> getById(int id) {
        List<StudyGroup> groups = new ArrayList<StudyGroup>(30);
        ResultSet result;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM study_group WHERE id=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery(query);
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

    public List<StudyGroup> getByName(String name) {
        List<StudyGroup> groups = new ArrayList<StudyGroup>(30);
        ResultSet result;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM study_group WHERE name=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            result = statement.executeQuery(query);
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

    public boolean insertStudyGroup(StudyGroup student) {
        return false;
    }

    public boolean updateStudyGroup(StudyGroup student) {
        return false;
    }

    public boolean deleteStudyGroup(StudyGroup student) {
        return false;
    }
}
