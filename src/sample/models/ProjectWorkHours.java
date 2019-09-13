package sample.models;

import java.time.LocalDate;

public class ProjectWorkHours extends WorkHours {
    private Project projectId;

    public ProjectWorkHours(Project projectId) {
        this.projectId = projectId;
    }

    public ProjectWorkHours(int id, User userId, LocalDate date, String startedWorkind, String finishedWorking, String workHours, Project projectId) {
        super(id, userId, date, startedWorkind, finishedWorking, workHours);
        this.projectId = projectId;
    }

    public ProjectWorkHours() {

    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }
}
