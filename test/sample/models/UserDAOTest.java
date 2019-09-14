package sample.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserDAOTest {

    @Test
    void addUser() {
        UserDAO dao = UserDAO.getInst();
        User user = new User("Vedran", "Ljubovic", "Neka 123", 71000, "Sarajevo", new Position(1, "owner"), "vljubovic1", "vedran123", 1);
        dao.addUser(user);
        List<User> users = dao.listAllUsersFromDatabase();
        assertEquals("Vedran", users.get(users.size() - 1).getName());
        dao.removeUser(user);
        dao.close();
        dao.removeInstance();
    }

    @Test
    void removeUser() {
        UserDAO dao = UserDAO.getInst();
        User user = new User("Vedran", "Ljubovic", "Neka 123", 71000, "Sarajevo", new Position(1, "owner"), "vljubovic1", "vedran123", 1);
        dao.addUser(user);
        List<User> users = dao.listAllUsersFromDatabase();
        assertEquals(4, users.size());
        dao.removeUser(user);
        users = dao.listAllUsersFromDatabase();
        assertEquals(3, users.size());
        assertEquals("Haris", users.get(0).getName());
        assertEquals("Adnan", users.get(1).getName());
        assertEquals("Fate", users.get(2).getName());
        dao.close();
        dao.removeInstance();
    }
}