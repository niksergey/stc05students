package models.pojo;

import java.util.Date;

public class Lesson {
    private int id;
    private Date lessonDate;
    private int room;
    private String description;
    private int studyGroup;

    public Lesson(int id, Date lessonDate, int room, String description, int studyGroup) {
        this.id = id;
        this.lessonDate = lessonDate;
        this.room = room;
        this.description = description;
        this.studyGroup = studyGroup;
    }

    public int getId() {
        return id;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public int getRoom() {
        return room;
    }

    public String getDescription() {
        return description;
    }

    public int getStudyGroup() {
        return studyGroup;
    }
}
