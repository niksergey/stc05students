package main.services;

import main.models.dao.StudentDao;
import main.models.dao.StudentDaoInterface;
import main.models.pojo.Student;

import java.util.List;

public class StudentService implements StudentServiceInterface {
    public  List<Student> getAllStudents() {
        StudentDaoInterface studentDao = new StudentDao();
        return studentDao.getAllStudents();
    }
}
