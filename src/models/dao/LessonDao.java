package models.dao;

import models.pojo.Lesson;

import java.util.List;

public interface LessonDao {
    List<Lesson> findAll();
    List<Lesson> findById();
    List<Lesson> findByName();
    boolean insertLesson(Lesson lesson);
    boolean updateLesson(Lesson lesson);
    boolean deleteLesson(Lesson lesson);
}
