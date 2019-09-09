package sample.models;

public class Project {
    private int id;
    private String name;
    private int activity;

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public Project(int id, String name, int activity) {
        this.id = id;
        this.name = name;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Project: " + name + "\n";
    }
}
