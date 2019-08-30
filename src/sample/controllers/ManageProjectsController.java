package sample.controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class ManageProjectsController {
    public Button btnEnd;
    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnEnd.getScene().getWindow();
        stage.close();
    }
}
