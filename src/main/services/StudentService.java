package main.services;

import main.models.pojo.Student;

import java.util.List;

/**
 * Created by sergey on 19.04.17.
 */
public interface StudentService {
    List<Student> getAllStudents();
    Student getBlank();
    boolean addStudent(String name, int age, int groupId);
    boolean deleteStudent(int id);
    Student getById(int id);
    boolean updateStudent(String name, int age, int groupId, int id);
}
