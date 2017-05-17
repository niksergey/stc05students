package main.services;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import main.models.dao.StudentDao;
import main.models.dao.StudentDaoImpl;
import main.models.dao.StudentRepo;
import main.models.dto.StudentDto;
import main.models.entities.StudentEntity;
import main.models.entities.StudyGroupEntity;
import main.models.pojo.Student;
import main.models.pojo.StudyGroup;
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

    MapperFacade mapper;

    public StudentServiceImpl() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(StudentEntity.class, StudentDto.class)
                .field( "studyGroupEntity.id", "groupId")
                .byDefault()
                .register();
        mapperFactory.classMap(Student.class, StudentDto.class)
                .byDefault()
                .register();
        mapper = mapperFactory.getMapperFacade();
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

    public  List<StudentDto> getAllStudents() {
        List<StudentDto> sts = new ArrayList<>();

//        for (StudentEntity student: studentRepo.findAll()) {
//            StudentDto studentDto = mapper.map(student, StudentDto.class);
//            sts.add(studentDto);
//        }
        for (StudentEntity student : studentDao.getAllStudents()) {
            StudentDto studentDto = mapper.map(student, StudentDto.class);
            sts.add(studentDto);
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

    @Override
    public StudentDto addStudent(StudentDto student) {
        StudentEntity studentEntity = mapper.map(student, StudentEntity.class);

        StudentEntity entity = studentRepo.save(studentEntity);
        StudentDto savedDto = mapper.map(entity, StudentDto.class);
        return savedDto;
    }

    public boolean deleteStudent(int id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    public StudentDto getById(int id) {
//        return studentDao.getById(id);
        StudentDto student = mapper.map(studentDao.getStudentById(id), StudentDto.class);
        return student;
    }

    @Override
    public boolean updateStudent(String name, int age, int groupId, int id) {
        return studentDao.updateStudent(new Student(id, name, age, groupId));
    }

    @Override
    public boolean updateStudent(StudentDto student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public Student getBlank() {
        return new Student("default", 99, 1);
    }
}
