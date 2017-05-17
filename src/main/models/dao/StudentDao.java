package main.models.dao;

import main.models.entities.StudentEntity;
import main.models.pojo.Student;
import java.util.List;

public interface StudentDao {
    List<StudentEntity> getAllStudents();
    Student getById(int id);
    Student getByName(String name);
    StudentEntity getStudentById(int id);
    boolean insertStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);


}


