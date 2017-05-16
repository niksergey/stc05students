package main.models.pojo;


public class Student {
    private int id;
    private String name;
    private int age;
    private int groupId;

    public Student() {
    }

    public Student(int id, String name, int age, int groupId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.groupId = groupId;
    }

    public Student(String name, int age, int groupId) {
        this.name = name;
        this.age = age;
        this.groupId = groupId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGroupId() {
        return groupId;
    }
}
