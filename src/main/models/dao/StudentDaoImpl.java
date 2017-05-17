package main.models.dao;

import main.models.dao.mappers.StudentMapper;
import main.models.dto.StudentDto;
import main.models.entities.StudentEntity;
import main.models.pojo.Student;
import main.utils.DatabaseManager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    private final static Logger LOGGER = Logger.getLogger(StudentDaoImpl.class);

    @Autowired
    private StudentMapper studentMapper;

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

    public List<StudentEntity> getAllStudents() {
        return studentMapper.getAllStudents();
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

    @Override
    public StudentEntity getStudentById(int id) {
        return studentMapper.getStudentById(id);
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

    @Override
    public boolean updateStudent(StudentDto student) {
        studentMapper.updateStudent(student);
        return true;
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
