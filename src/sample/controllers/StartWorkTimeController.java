package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import sample.models.WorkHours;
import sample.models.WorkHoursDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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


    public void startWorkingAction(ActionEvent actionEvent) {
        lblWorking.setText("You just started working. Good luck!");
        if (workHours == null) workHours = new WorkHours();
        workHours.setId(dao.getIdWorkHours());
        // TODO: Dodati user-a za određeno vrijeme????
        //workHours.setUser(); //kako da zapamtim User-a koji se logovao da bi mogao njega dodati
        workHours.setDate(LocalDate.now());
        workHours.setStartedWorking(LocalTime.now().toString());
        workHours.setFinishedWorking(null);
        workHours.setWorkHours(null);
        dao.addWorkHours(workHours);

    }

    public void endWorkingAction(ActionEvent actionEvent) {
        if (workHours == null) lblWorking.setText("You can not stop working if you have not even started!");
        lblWorking.setText("You just stopped working, enough for today. See you soon!");
        int userId = 1;
        dao.updateFinishedWorkingTime(LocalTime.now(), userId); // ???....
    }

}
