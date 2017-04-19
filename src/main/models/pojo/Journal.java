package main.models.pojo;


public class Journal {
    private int id;
    private int lesson_id;
    private int student_id;

    private String name;

    public Journal(int id, int lesson_id, int student_id,
                   String name) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.student_id = student_id;
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

    public String getName() {
        return name;
    }
}
