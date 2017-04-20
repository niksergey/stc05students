package main.models.dao;

import main.models.pojo.Student;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    Student getById(int id);
    Student getByName(String name);
    boolean insertStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
}


