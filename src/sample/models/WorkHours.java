package sample.models;

import java.util.Date;

public class WorkHours {
    private int id;
    private User userId;
    private Date date;
    private int workHours;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public WorkHours() {
    }

    public WorkHours(int id, User userId, Date date, int workHours) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.workHours = workHours;
    }
}
