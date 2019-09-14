package sample.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDAOTest {

    @Test
    void addProject() {
        ProjectDAO projectDAO = new ProjectDAO();
        List<Project> list = projectDAO.listProjects();
        Project project = new Project(3, "Bit Alijansa", 1);
        projectDAO.addProject(project);
        list.add(project);
        assertEquals("Bit Alijansa", list.get(list.size() - 1).getName());
        projectDAO.removeProject(project);
        projectDAO.removeInstance();
    }

    @Test
    void removeProject() {
        ProjectDAO projectDAO = new ProjectDAO();
        Project project = new Project(3, "Bit Alijansa", 1);
        projectDAO.addProject(project);
        List<Project> list = projectDAO.listProjects();
        assertEquals(3, list.size());
        Project project1 = new Project(4, "Razvoj Softvera", 0);
        list.add(project1);
        assertEquals(4, list.size());
        list.remove(project);
        projectDAO.removeProject(project);
        list.remove(project1);
        projectDAO.removeProject(project1);
        assertEquals("Mobile Banking System", list.get(0).getName());
        assertEquals("Video Store Software", list.get(1).getName());
        projectDAO.close();
        projectDAO.removeInstance();
    }
}