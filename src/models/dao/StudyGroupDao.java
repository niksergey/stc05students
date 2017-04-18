package models.dao;

import models.pojo.StudyGroup;

import java.util.List;

public interface StudyGroupDao {
    List<StudyGroup> findAll();
    List<StudyGroup> findById();
    List<StudyGroup> findByName();
    boolean insertStudyGroup(StudyGroup student);
    boolean updateStudyGroup(StudyGroup student);
    boolean deleteStudyGroup(StudyGroup student);
}
