package main.models.dao;

import main.models.pojo.Student;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    List<Student> getById(int id);
    List<Student> getByName(String name);
    boolean insertStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
}


