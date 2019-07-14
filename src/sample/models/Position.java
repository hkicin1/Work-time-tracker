package sample.models;

import java.util.Date;

public class Position {
    private int id;
    private String name;

    public Position() {
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

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Position(int id, String name, Date beginningDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    private Date beginningDate;
    private Date endDate;
}
