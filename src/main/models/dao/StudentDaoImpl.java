package main.models.dao;

import main.models.pojo.Student;
import main.utils.DatabaseManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    private final static Logger LOGGER = Logger.getLogger(StudentDaoImpl.class);

    private Student createEntity(ResultSet result) {
        Student student = null;
        try {
            student = new Student(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("age"),
                    result.getInt("group_id"));
        } catch (SQLException e ) {
            LOGGER.debug("Error during reading ResultSet");
        }
        return student;
    }

    public List<Student> getAllStudents() {
        String query = "SELECT * FROM student;";
        List<Student> students = new ArrayList<>(100);
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    students.add(createEntity(result));
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getById(int id) {
        String query = "SELECT * FROM student WHERE id=?;";
        Student student = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    student = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return student;
    }

    public Student getByName(String name) {
        String query = "SELECT * FROM student WHERE name=?;";
        Student student = null;
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    student = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean insertStudent(Student student) {
        String query = "INSERT INTO student (name, age, group_id) " +
                " VALUES (?, ?, ?);";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getGroupId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            LOGGER.warn("SQLException while inserting student");
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        String query = "UPDATE student SET name=?, age=?, group_id=? WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getGroupId());
            statement.setInt(4, student.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        String query = "DELETE FROM student WHERE id=?;";
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
