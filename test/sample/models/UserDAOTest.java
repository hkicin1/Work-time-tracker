package sample.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserDAOTest {

    @Test
    void addUser() {
        UserDAO dao = UserDAO.getInst();
        User user = new User();
        user.setName("Test");
        user.setSurname("Testic");
        user.setAddress("Testna 1");
        user.setPostalNumber(71000);
        user.setCity("Sarajevo");
        user.setPosition(new Position(10, "programmer"));
        user.setUserName("ttestic1");
        user.setPassword("asdf1234ASDF!#$");
        dao.addUser(user);
        List<User> users = dao.listUsers();
        assertEquals("Test", users.get(3).getName());
    }

    @Test
    void removeUser() {
    }
}