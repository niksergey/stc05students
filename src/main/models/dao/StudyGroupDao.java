package main.models.dao;

import main.models.pojo.StudyGroup;

import java.util.List;

public interface StudyGroupDao {
    List<StudyGroup> getAllGroups();
    List<StudyGroup> getById(int id);
    List<StudyGroup> getByName(String name);
    boolean insertStudyGroup(StudyGroup student);
    boolean updateStudyGroup(StudyGroup student);
    boolean deleteStudyGroup(StudyGroup student);
}
