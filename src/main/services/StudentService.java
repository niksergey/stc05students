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

    public boolean addStudent(String name, String age, String groupId) {
        StudentDaoInterface studentDao = new StudentDao();
        int studAge = Integer.valueOf(age);
        int studGroupId = Integer.valueOf(groupId);
        return studentDao.insertStudent(new Student(name, studAge , studGroupId));
    }

    public boolean deleteStudent(String id) {
        int studId = Integer.valueOf(id);
        StudentDaoInterface studentDao = new StudentDao();
        return studentDao.deleteStudent(studId);
    }
}
