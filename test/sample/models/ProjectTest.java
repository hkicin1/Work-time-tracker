package sample.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    Project project = new Project(1, "MojProjekat", 1);

    @Test
    void getName() {
        assertEquals("MojProjekat", project.getName());
    }

    @Test
    void setName() {
        project.setName("RS Projekat");
        assertEquals("RS Projekat", project.getName());
    }

    @Test
    void getActivity() {
        assertEquals(1, project.getActivity());
    }

    @Test
    void setActivity() {
        project.setActivity(0);
        assertEquals(0, project.getActivity());
    }
}