package main.models.dao;


import main.models.pojo.Journal;
import main.models.pojo.Lesson;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JournalDao implements JournalDaoInterface{
    private List<Journal> constructFromResult(ResultSet result) {
        List<Journal> journals = new ArrayList<Journal>(16);
        try {
            while (result.next()) {
                Journal journal = new Journal(
                        result.getInt("id"),
                        result.getInt("lesson_id"),
                        result.getInt("student_id"),
                        result.getString("description"));
                journals.add(journal);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return journals;
    }
    public List<Journal> getAll() {
        List<Journal> journals = null;
        try{
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM journal;";
            Statement statement = conn.createStatement();
            journals = constructFromResult(statement.executeQuery(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journals;
    }

    public List<Journal> getById(int id) {
        List<Journal> journals = null;
        try{
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM journal WHERE id=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            journals = constructFromResult(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journals;
    }

    public boolean insertJournal(Journal journal) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "INSERT INTO journal (id, lesson_id, student_id)" +
                    " VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, journal.getId());
            preparedStatement.setInt(2, journal.getLesson_id());
            preparedStatement.setInt(3, journal.getStudent_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateJournal(Journal journal) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "UPDATE journal SET lesson_id=?, student_id=? WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, journal.getLesson_id());
            preparedStatement.setInt(2, journal.getStudent_id());
            preparedStatement.setInt(3, journal.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteJournal(Journal journal) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "DELETE FROM journal WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, journal.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
