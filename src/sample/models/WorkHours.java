package sample.models;

import java.util.Date;

public class WorkHours {
    private int id;
    private Employee employeeId;
    private Date date;
    private int workHours;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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

    public WorkHours(int id, Employee employeeId, Date date, int workHours) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.workHours = workHours;
    }
}
