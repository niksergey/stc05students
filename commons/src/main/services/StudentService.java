package main.services;

import main.models.dto.StudentDto;
import main.models.pojo.Student;

import java.util.List;

/**
 * Created by sergey on 19.04.17.
 */
public interface StudentService {
    List<StudentDto> getAllStudents();
    Student getBlank();
    boolean addStudent(String name, int age, int groupId);
    StudentDto addStudent(StudentDto student);
    boolean deleteStudent(int id);
    StudentDto getById(int id);
    boolean updateStudent(String name, int age, int groupId, int id);
    boolean updateStudent(StudentDto student);
}
