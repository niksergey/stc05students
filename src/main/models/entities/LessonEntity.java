package main.models.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lesson", schema = "public", catalog = "students")
public class LessonEntity {
    private int id;
    private Timestamp lessonDate;
    private int room;
    private String description;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lesson_date")
    public Timestamp getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Timestamp lessonDate) {
        this.lessonDate = lessonDate;
    }

    @Basic
    @Column(name = "room")
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonEntity that = (LessonEntity) o;

        if (id != that.id) return false;
        if (room != that.room) return false;
        if (lessonDate != null ? !lessonDate.equals(that.lessonDate) : that.lessonDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lessonDate != null ? lessonDate.hashCode() : 0);
        result = 31 * result + room;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
