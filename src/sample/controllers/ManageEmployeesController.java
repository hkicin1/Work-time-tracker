package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ManageEmployeesController {
    public Button btnEnd;

    public void addEmployeeAction(ActionEvent actionEvent) {
    }

    public void deleteEmployeeAction(ActionEvent actionEvent) {
    }

    public void exitAction(ActionEvent actionEvent) {
       Stage stage = (Stage) btnEnd.getScene().getWindow();
      stage.close();
    }
}
