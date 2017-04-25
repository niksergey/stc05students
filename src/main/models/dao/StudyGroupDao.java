package main.models.dao;

import main.models.pojo.StudyGroup;

import java.util.List;

public interface StudyGroupDao {
    List<StudyGroup> getAllGroups();
    StudyGroup getById(int id);
    StudyGroup getByName(String name);
    boolean insertStudyGroup(StudyGroup student);
    boolean updateStudyGroup(StudyGroup student);
    boolean deleteStudyGroup(int id);
}
