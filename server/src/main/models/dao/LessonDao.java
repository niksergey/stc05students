package main.models.dao;

import main.models.pojo.Lesson;

import java.util.List;

public interface LessonDao {
    List<Lesson> getAll();
    Lesson getById(int id);
    Lesson getByRoom(int roomNumber);
    boolean insertLesson(Lesson lesson);
    boolean updateLesson(Lesson lesson);
    boolean deleteLesson(Lesson lesson);
}
