package main.models.dao;

import main.models.pojo.Lesson;

import java.util.List;

public interface LessonDaoInterface {
    List<Lesson> getAll();
    List<Lesson> getById(int id);
    List<Lesson> getByRoom(int roomNumber);
    boolean insertLesson(Lesson lesson);
    boolean updateLesson(Lesson lesson);
    boolean deleteLesson(Lesson lesson);
}
