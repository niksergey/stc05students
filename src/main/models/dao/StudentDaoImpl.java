package main.models.dao;

import main.models.pojo.Student;
import main.utils.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

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
        return students;
    }

    public List<Student> getAllStudents() {
        List<Student> students = null;
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM student;";
            statement = conn.createStatement();
            students = constructFromResult(statement.executeQuery(query));
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
        return students;
    }

    public List<Student> getById(int id) {
        List<Student> students = null;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM student WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            students = constructFromResult(statement.executeQuery(query));
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
        return students;
    }

    public List<Student> getByName(String name) {
        List<Student> students = null;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "SELECT * FROM student WHERE name=?;";
            statement = conn.prepareStatement(query);
            statement.setString(1, name);
            students = constructFromResult(statement.executeQuery(query));
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
        return students;
    }

    public boolean insertStudent(Student student) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "INSERT INTO student (name, age, group_id) " +
                    " VALUES (?, ?, ?);";
            statement = conn.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getGroupId());
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
        return true;
    }

    public boolean updateStudent(Student student) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "UPDATE student SET name=?, age=?, group_id=? WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getGroupId());
            statement.setInt(4, student.getId());
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

    public boolean deleteStudent(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DatabaseManager.getConnectionFromPool();
            String query = "DELETE FROM student WHERE id=?;";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
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
