package main.services;

import main.models.pojo.Student;

import java.util.List;

/**
 * Created by sergey on 19.04.17.
 */
public interface StudentService {
    List<Student> getAllStudents();
    boolean addStudent(String name, String age, String groupId);
    boolean deleteStudent(String id);
}
