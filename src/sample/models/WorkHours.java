package sample.models;

import java.time.LocalDate;

public class WorkHours {
    private int id;
    private User user;
    private LocalDate date;
    private String startedWorking;
    private String finishedWorking;
    private String workHours;

    public WorkHours() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public String getStartedWorking() {
        return startedWorking;
    }

    public void setStartedWorking(String startedWorking) {
        this.startedWorking = startedWorking;
    }

    public String getFinishedWorking() {
        return finishedWorking;
    }

    public void setFinishedWorking(String finishedWorking) {
        this.finishedWorking = finishedWorking;
    }

    public WorkHours(int id, User user, LocalDate date, String startedWorking, String finishedWorking, String workHours) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.startedWorking = startedWorking;
        this.finishedWorking = finishedWorking;
        this.workHours = workHours;
    }
}
