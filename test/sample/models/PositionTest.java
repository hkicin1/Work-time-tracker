package sample.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    Position position = new Position(2, "programmer");

    @Test
    void getId() {
        assertEquals(2, position.getId());
    }

    @Test
    void setId() {
        position.setId(1);
        assertEquals(1, position.getId());
    }

    @Test
    void getName() {
        assertEquals("programmer", position.getName());
    }

    @Test
    void setName() {
        position.setName("tester");
        assertEquals("tester", position.getName());
    }
}