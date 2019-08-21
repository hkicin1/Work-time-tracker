package sample.models;

import java.time.LocalDate;

public class ProjectWorkHours extends WorkHours {
    private Project projectId;

    public ProjectWorkHours(Project projectId) {
        this.projectId = projectId;
    }

    public ProjectWorkHours(int id, User userId, LocalDate date, String workHours, Project projectId) {
        super(id, userId, date, workHours);
        this.projectId = projectId;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }
}
