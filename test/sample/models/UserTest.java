package sample.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User u = new User(0, "Test", "Testic", "Testna 1", 71000, "Sarajevo", new Position(), "ttestic1", "1234asdf", 0);

    @Test
    void getName() {
        assertEquals("Test", u.getName());
    }

    @Test
    void setName() {
        u.setName("Test3");
        assertEquals("Test3", u.getName());
    }

    @Test
    void getUserName() {
        assertEquals("ttestic1", u.getUserName());
    }


    @Test
    void getPassword() {
        assertEquals("1234asdf", u.getPassword());
    }

    @Test
    void setPassword() {
        u.setPassword("asdf1234ASDF");
        assertEquals("asdf1234ASDF", u.getPassword());
    }
}