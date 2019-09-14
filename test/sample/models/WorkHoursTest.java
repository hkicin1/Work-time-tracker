package sample.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WorkHoursTest {

    WorkHours wh = new WorkHours(1, new User(), LocalDate.now(), "09:00:00", "17:00:00", "300");

    @Test
    void getDate() {
        assertEquals(LocalDate.now(), wh.getDate());
    }

    @Test
    void setDate() {
        wh.setDate(LocalDate.of(2018, 9, 1));
        assertEquals(   LocalDate.of(2018, 9, 1) , wh.getDate());
    }
}