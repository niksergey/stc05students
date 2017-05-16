package main.services;

import main.models.dao.StudentDao;
import main.models.dao.StudentRepo;
import main.models.entities.StudentEntity;
import main.models.entities.StudyGroupEntity;
import main.models.pojo.Student;
import main.utils.StudentEditInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    StudentDao studentDao;
    StudentRepo studentRepo;

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

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public  List<Student> getAllStudents() {
        List<Student> sts = new ArrayList<>();
        Iterable<StudentEntity> all = studentRepo.findAll();
        for (StudentEntity student: all) {
            sts.add(new Student(student.getId(), student.getName(), student.getAge(),
                    student.getStudyGroupEntity().getId()));
        }
        return sts;
    }

    public boolean addStudent(String name, int age, int groupId) {
//        return studentDao.insertStudent(new Student(name, age , groupId));
        StudentEntity se = new StudentEntity();
        StudyGroupEntity sge = new StudyGroupEntity();
        sge.setName("stc8");
        sge.setId(groupId);
        se.setAge(age);
        se.setName(name);
        se.setStudyGroupEntity(sge);
        studentRepo.save(se);
        return true;
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
