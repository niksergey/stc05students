package main.services;

import main.models.dao.StudentDaoImpl;
import main.models.dao.StudentDao;
import main.models.pojo.Student;

import java.util.List;

public class StudentService implements StudentServiceInterface {
    public  List<Student> getAllStudents() {
        StudentDao studentDao = new StudentDaoImpl();
        return studentDao.getAllStudents();
    }

    public boolean addStudent(String name, String age, String groupId) {
        StudentDao studentDao = new StudentDaoImpl();
        int studAge = Integer.valueOf(age);
        int studGroupId = Integer.valueOf(groupId);
        return studentDao.insertStudent(new Student(name, studAge , studGroupId));
    }

    public boolean deleteStudent(String id) {
        int studId = Integer.valueOf(id);
        StudentDao studentDao = new StudentDaoImpl();
        return studentDao.deleteStudent(studId);
    }
}
