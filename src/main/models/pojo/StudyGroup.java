package main.models.pojo;

public class StudyGroup {
    private int id;
    private String name;

    public StudyGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
