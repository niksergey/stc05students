package models.dao;

import models.pojo.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    List<Student> findById();
    List<Student> findByName();
    boolean insertStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(Student student);
}
