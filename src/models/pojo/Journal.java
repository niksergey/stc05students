package models.pojo;


public class Journal {
    private int id;
    private int lesson_id;
    private int student_id;
    private Lesson lesson;
    private Student student;
    private String name;

    public Journal(int id, int lesson_id, int student_id,
                   Lesson lesson, Student student, String name) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.student_id = student_id;
        this.lesson = lesson;
        this.student = student;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public Student getStudent() {
        return student;
    }

    public String getName() {
        return name;
    }
}
