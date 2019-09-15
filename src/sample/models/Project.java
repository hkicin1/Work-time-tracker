package sample.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

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

    public void writeIntoTextFile() throws IOException {
        BufferedWriter output = null;
        String text = toText();
        try {
            File file = new File(getName() + ".txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
    }

    public String toText() {
        String text = "Projekat " + getName() + " dodan je dana " + LocalDate.now() + " i njegov status je ";
        if (getActivity() == 1) text = text + "aktivan!";
        else text = text + "neaktivan!";
        return text;
    }

    @Override
    public String toString() {
        return "Project: " + name + "\n";
    }
}
