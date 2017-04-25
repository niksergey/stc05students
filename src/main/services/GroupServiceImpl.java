package main.services;

import main.models.dao.StudyGroupDao;
import main.models.pojo.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private StudyGroupDao studyGroupDao;

    public StudyGroupDao getStudyGroupDao() {
        return studyGroupDao;
    }

    @Autowired
    public void setStudyGroupDao(StudyGroupDao studyGroupDao) {
        this.studyGroupDao = studyGroupDao;
    }

    @Override
    public List<StudyGroup> getAllGroups() {
        return studyGroupDao.getAllGroups();
    }

    @Override
    public boolean addGroup(String name) {
        StudyGroup studyGroup = new StudyGroup(name);
        return studyGroupDao.insertStudyGroup(studyGroup);
    }

    @Override
    public boolean deleteGroup(int id) {
        return studyGroupDao.deleteStudyGroup(id);
    }
}
