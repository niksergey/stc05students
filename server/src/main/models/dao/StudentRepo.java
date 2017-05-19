package main.models.dao;

import main.models.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo  extends CrudRepository<StudentEntity, Long> {
}
