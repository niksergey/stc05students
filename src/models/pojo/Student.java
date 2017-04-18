package models.pojo;


public class Student {
    private int id;
    private String name;
    private int age;
    private int groupId;

    public Student(int id, String name, int age, int groupId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.groupId = groupId;
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
