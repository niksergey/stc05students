package main.services;

import main.models.dao.StudentDaoImpl;
import main.models.dao.StudentDao;
import main.models.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    StudentDao studentDao;

    public StudentServiceImpl() {
    }

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public  List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public boolean addStudent(String name, String age, String groupId) {
        int studAge = Integer.valueOf(age);
        int studGroupId = Integer.valueOf(groupId);
        return studentDao.insertStudent(new Student(name, studAge , studGroupId));
    }

    public boolean deleteStudent(String id) {
        int studId = Integer.valueOf(id);
        return studentDao.deleteStudent(studId);
    }
}
