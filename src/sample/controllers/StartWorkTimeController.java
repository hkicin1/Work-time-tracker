package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import sample.models.User;
import sample.models.WorkHours;
import sample.models.WorkHoursDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StartWorkTimeController {

    public LocalDateTime beginning;
    public LocalDateTime ending;
    public LocalTime timeBgn;
    public LocalTime timeEnd;
    public int timeOfBeginningInSeconds = 0;
    public int timeOfEndingInSeconds = 0;
    public int workTimeInSeconds = 0;

    public WorkHoursDAO dao;
    private WorkHours workHours;
    public Label lblWorking;
    private User registeredEmployee;


    public StartWorkTimeController(User registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
        dao = dao.getInst();
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void startWorkingAction(ActionEvent actionEvent) throws ParseException {
        lblWorking.setText("You just started working. Good luck!");
        if (workHours == null) workHours = new WorkHours();
        workHours.setId(dao.getIdWorkHours());
        workHours.setUser(registeredEmployee);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        Date date = sdf.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
        workHours.setDate(convertToLocalDateViaInstant(date));

        workHours.setStartedWorking(LocalTime.now().toString());
        workHours.setFinishedWorking(null);
        workHours.setWorkHours(null);
        dao.addWorkHours(workHours);

    }

    public void endWorkingAction(ActionEvent actionEvent) {
        if (workHours == null) lblWorking.setText("You can not stop working if you have not even started!");
        lblWorking.setText("You just stopped working, enough for today. See you soon!");
        dao.updateFinishedWorkingTime(LocalTime.now(), registeredEmployee.getId(), LocalDate.now());
    }

    public void setRegisteredEmployee(User registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
    }
}
