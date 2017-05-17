package main.models.dao.mappers;

import main.models.entities.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentMapper {
    List getAllStudents();
    StudentEntity getStudentById(Integer id);
}
