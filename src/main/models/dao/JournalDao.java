package main.models.dao;

import main.models.pojo.Journal;
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
        return journals;
    }

    public List<Journal> getAll() {
        List<Journal> journals = null;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM journal;";
            statement = conn.createStatement();
            journals = constructFromResult(statement.executeQuery(query));
            statement.close();
            statement = null;
            conn.close();
            conn = null;
        } catch (SQLException e ) {
            e.printStackTrace();
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
        return journals;
    }

    public List<Journal> getById(int id) {
        List<Journal> journals = null;
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM journal WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            journals = constructFromResult(statement.executeQuery());
            statement.close();
            statement = null;
            conn.close();
            conn = null;
        } catch (SQLException e ) {
            e.printStackTrace();
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
        return journals;
    }

    public boolean insertJournal(Journal journal) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "INSERT INTO journal (id, lesson_id, student_id)" +
                    " VALUES (?, ?, ?);";
            statement = conn.prepareStatement(query);
            statement.setInt(1, journal.getId());
            statement.setInt(2, journal.getLesson_id());
            statement.setInt(3, journal.getStudent_id());
            statement.executeUpdate();
            statement.close();
            statement = null;
            conn.close();
            conn = null;
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
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
        return false;
    }

    public boolean updateJournal(Journal journal) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "UPDATE journal SET lesson_id=?, student_id=? WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, journal.getLesson_id());
            statement.setInt(2, journal.getStudent_id());
            statement.setInt(3, journal.getId());
            statement.executeUpdate();
            statement.close();
            statement = null;
            conn.close();
            conn = null;
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
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
        return false;
    }

    public boolean deleteJournal(Journal journal) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "DELETE FROM journal WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, journal.getId());
            statement.executeUpdate();
            statement.close();
            statement = null;
            conn.close();
            conn = null;
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
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
        return false;
    }
}
