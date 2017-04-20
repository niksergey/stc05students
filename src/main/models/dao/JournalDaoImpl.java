package main.models.dao;

import main.models.pojo.Journal;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JournalDaoImpl implements JournalDao {
    private Journal createEntity(ResultSet result) {
        Journal journal = null;
        try {
            journal = new Journal(
                    result.getInt("id"),
                    result.getInt("lesson_id"),
                    result.getInt("student_id"),
                    result.getString("description"));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return journal;
    }

    public List<Journal> getAll() {
        String query = "SELECT * FROM journal;";
        List<Journal> journals = new ArrayList<Journal>(16);

        try(Connection conn = DatabaseManager.getConnectionFromPool();
        Statement statement = conn.createStatement()) {
            try (ResultSet result = statement.executeQuery(query)) {
                while(result.next())
                    journals.add(createEntity(result));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return journals;
    }

    public Journal getById(int id) {
        String query = "SELECT * FROM journal WHERE id=?;";
        Journal journal = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)
        ){
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery()) {
                if (result.next())
                    journal = createEntity(result);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return journal;
    }

    public boolean insertJournal(Journal journal) {
        String query = "INSERT INTO journal (id, lesson_id, student_id)" +
                " VALUES (?, ?, ?);";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)
        ){
            statement.setInt(1, journal.getId());
            statement.setInt(2, journal.getLesson_id());
            statement.setInt(3, journal.getStudent_id());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateJournal(Journal journal) {
        String query = "UPDATE journal SET lesson_id=?, student_id=? WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)
        ){
            statement.setInt(1, journal.getLesson_id());
            statement.setInt(2, journal.getStudent_id());
            statement.setInt(3, journal.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteJournal(Journal journal) {
        String query = "DELETE FROM journal WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)
        ){
            statement.setInt(1, journal.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
