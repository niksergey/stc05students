package main.services;

import main.models.pojo.StudyGroup;

import java.util.List;

public interface GroupService {
    List<StudyGroup> getAllGroups();
    boolean addGroup(String name);
    boolean deleteGroup(int id);
}
