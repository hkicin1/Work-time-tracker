package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import sample.models.WorkHours;

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
    public WorkHours wh = null;
    public Label lblWorking;


    public String secondsToHMS(int seconds) {
        int hours = seconds / 3600;
        seconds = seconds - hours * 3600;
        int minutes = seconds / 60;
        seconds = seconds - minutes * 60;
        String time = hours + ":" + minutes + ":" + seconds;
        return time;
    }
    public void startWorkingAction(ActionEvent actionEvent) {
        beginning = LocalDateTime.now();
        timeBgn = beginning.toLocalTime();
        lblWorking.setText("Počeli ste raditi!");
    }

    public void endWorkingAction(ActionEvent actionEvent) {
        lblWorking.setText("Prestali ste raditi!");
        ending = LocalDateTime.now();
        timeEnd = ending.toLocalTime();
        wh.setDate(ending.toLocalDate());
        timeOfBeginningInSeconds = timeBgn.getHour() * 3600 + timeBgn.getMinute() * 60 + timeBgn.getSecond();
        timeOfEndingInSeconds = timeEnd.getHour() * 3600 + timeEnd.getMinute() * 60 + timeEnd.getSecond();
        workTimeInSeconds = timeOfEndingInSeconds - timeOfBeginningInSeconds;
        wh.setWorkHours(secondsToHMS(workTimeInSeconds));
        System.out.println(wh.getWorkHours());
    }

}
