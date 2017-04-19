package main.models.dao;

import main.models.pojo.Student;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements StudentDaoInterface {

    private List<Student> constructFromResult(ResultSet result) {
        List<Student> students = new ArrayList<Student>(16);
        try {
            while (result.next()) {
                Student student = new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("age"),
                        result.getInt("group_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getAllStudents() {
        List<Student> students = null;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM student;";
            Statement statement = conn.createStatement();
            students = constructFromResult(statement.executeQuery(query));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getById(int id) {
        List<Student> students = null;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM student WHERE id=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            students = constructFromResult(statement.executeQuery(query));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getByName(String name) {
        List<Student> students = null;
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM student WHERE name=?;";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            students = constructFromResult(statement.executeQuery(query));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return students;
    }

    public boolean insertStudent(Student student) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "INSERT INTO student (id, name, age, group_id) " +
                    " VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setInt(4, student.getGroupId());
            preparedStatement.executeUpdate();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateStudent(Student student) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "UPDATE student SET name=?, age=?, group_id=? WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(Student student) {
        try {
            Connection conn = DatabaseManager.getConnectionFromPool();
            String query = "DELETE FROM student WHERE id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

}
