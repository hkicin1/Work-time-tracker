package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeHelpController implements Initializable {
    public Button btnExit;
    public Label lblHelpEmployee;

    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblHelpEmployee.setText("By clicking on select project button, " +
                "you will be able to select the \n" +
                "project you will be working on.\n"
                + "By clicking on start/end work time button, you will be able to \n" +
                "register the beginning and ending of your work.\n"
                + "By clicking on reports button, you will be able to\n" +
                "see your work hours report.\n");
    }

}
