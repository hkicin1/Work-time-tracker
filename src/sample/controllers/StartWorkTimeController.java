package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.models.User;
import sample.models.WorkHours;
import sample.utilities.WorkHoursDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class StartWorkTimeController {

    public WorkHoursDAO dao;
    private WorkHours workHours;
    public Label lblWorking;
    private User registeredEmployee;

    public Button btnStart;
    public Button btnEndWorking;

    @FXML
    public void initialize() throws SQLException {
        if (dao.checkIfDateExistsInDatabase(LocalDate.now(), registeredEmployee) == true) {
            lblWorking.setText("You have already worked today, see you tomorrow!");
            btnStart.setDisable(true);
            btnEndWorking.setDisable(true);
        }
    }

    public StartWorkTimeController(User registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
        dao = dao.getInst();
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void startWorkingAction(ActionEvent actionEvent) throws ParseException, SQLException {
        lblWorking.setText("You just started working. Good luck!");
        if (workHours == null) workHours = new WorkHours();
        workHours.setId(dao.getIdWorkHours());
        workHours.setUser(registeredEmployee);
        workHours.setDate(LocalDate.now(ZoneId.of("Europe/Sarajevo")));
        workHours.setStartedWorking(LocalTime.now().toString());
        workHours.setFinishedWorking(null);
        workHours.setWorkHours(null);
        dao.addWorkHours(workHours);
        btnStart.setDisable(true);

    }

    public void endWorkingAction(ActionEvent actionEvent) {
        if (workHours == null) lblWorking.setText("You can not stop working if you have not even started!");
        lblWorking.setText("You just stopped working, enough for today. See you soon!");
        dao.updateFinishedWorkingTime(LocalTime.now(), registeredEmployee.getId(), LocalDate.now());
        btnEndWorking.setDisable(true);
    }

    public void setRegisteredEmployee(User registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
    }
}
