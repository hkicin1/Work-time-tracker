package sample.controllers;

import javafx.event.ActionEvent;

import java.time.LocalTime;

public class StartWorkTimeController {

    public LocalTime beginning;
    public LocalTime ending;

    public void startWorkingAction(ActionEvent actionEvent) {
        beginning = LocalTime.now();
    }

    public void endWorkingAction(ActionEvent actionEvent) {
        ending = LocalTime.now();
    }

}
