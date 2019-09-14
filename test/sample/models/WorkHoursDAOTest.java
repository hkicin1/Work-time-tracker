package sample.models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkHoursDAOTest {

    @Test
    void addWorkHours() {
        UserDAO userDAO = new UserDAO();
        User user = new User("Vedran", "Ljubovic", "Neka 123", 71000, "Sarajevo", new Position(1, "owner"), "vljubovic1", "vedran123", 1);
        userDAO.addUser(user);
        userDAO.close();
        userDAO.removeInstance();
        WorkHoursDAO dao = new WorkHoursDAO();
        WorkHours wh = new WorkHours(1, user, LocalDate.now(), "09:00:00", "17:00:00", "300");
        dao.addWorkHours(wh);
        List<WorkHours> list = dao.listWorkHours();
        list.add(wh);
        assertEquals("300", list.get(list.size() - 1).getWorkHours());
        dao.removeWorkingHours(wh);
        dao.close();
        dao.removeInstance();
        UserDAO dao1 = new UserDAO();
        dao1.removeUser(user);
        dao1.close();
        dao1.removeInstance();
    }

    @Test
    void updateFinishedWorkingTime() {
        UserDAO userDAO = new UserDAO();
        User user = new User("Vedran", "Ljubovic", "Neka 123", 71000, "Sarajevo", new Position(1, "owner"), "vljubovic1", "vedran123", 1);
        userDAO.addUser(user);
        userDAO.close();
        userDAO.removeInstance();
        WorkHoursDAO dao = new WorkHoursDAO();
        WorkHours wh = new WorkHours(1, user, LocalDate.now(), "09:00:00", "17:00:00", "300");
        dao.addWorkHours(wh);
        List<WorkHours> list = dao.listWorkHours();
        assertEquals("300", list.get(list.size() - 1).getWorkHours());
        list.remove(wh);
        wh.setWorkHours("302");
        list.add(wh);
        assertEquals("302", list.get(list.size() - 1).getWorkHours());
        dao.removeWorkingHours(wh);
        dao.close();
        dao.removeInstance();
        UserDAO userDAO1 = new UserDAO();
        userDAO1.removeUser(user);
        userDAO1.close();
        userDAO1.removeInstance();
    }

    @AfterAll
    static void removeWorkingHours() {
        UserDAO userDAO = new UserDAO();
        User user = new User("Vedran", "Ljubovic", "Neka 123", 71000, "Sarajevo", new Position(1, "owner"), "vljubovic1", "vedran123", 1);
        userDAO.addUser(user);
        userDAO.close();
        userDAO.removeInstance();
        WorkHoursDAO dao = new WorkHoursDAO();
        WorkHours wh = new WorkHours(1, user, LocalDate.now(), "09:00:00", "17:00:00", "300");
        dao.addWorkHours(wh);
        List<WorkHours> list = dao.listWorkHours();
        assertEquals(1, list.size());
        list.clear();
        dao.removeWorkingHours(wh);
        assertEquals(0, list.size());
        dao.close();
        dao.removeInstance();
        UserDAO userDAO1 = new UserDAO();
        userDAO1.removeUser(user);
        userDAO1.close();
        userDAO1.removeInstance();
    }
}