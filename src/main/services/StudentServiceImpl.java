package main.services;

import main.models.dao.StudentDaoImpl;
import main.models.dao.StudentDao;
import main.models.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    public boolean addStudent(String name, int age, int groupId) {
        return studentDao.insertStudent(new Student(name, age , groupId));
    }

    public boolean deleteStudent(int id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    public Student getById(int id) {
        return studentDao.getById(id);
    }

    @Override
    public boolean updateStudent(String name, int age, int groupId, int id) {
        return studentDao.updateStudent(new Student(id, name, age, groupId));
    }

    @Override
    public Student getBlank() {
        return new Student("default", 99, 1);
    }
}
