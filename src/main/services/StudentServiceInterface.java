package main.services;

import main.models.pojo.Student;

import java.util.List;

/**
 * Created by sergey on 19.04.17.
 */
public interface StudentServiceInterface {
    public List<Student> getAllStudents();
}
