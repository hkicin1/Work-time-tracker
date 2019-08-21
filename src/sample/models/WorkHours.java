package sample.models;

import java.time.LocalDate;

public class WorkHours {
    private int id;
    private User userId;
    private LocalDate date;
    private String workHours;

    public WorkHours() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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

    public WorkHours(int id, User userId, LocalDate date, String workHours) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.workHours = workHours;
    }
}
