package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminHelpController implements Initializable {

    public Button btnExit;
    public Label lblHelpAdmin;
    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblHelpAdmin.setText("By clicking on manage employees button, \n" +
                "you will be able to add new employee, \n" +
                "delete another, or edit data.\n\n"
                + "By clicking on manage project button, \n" +
                "you will be able to add new project or\n " +
                "delete existing.\n\n"
                + "By clicking on reports button, \n" +
                "you will be able to see work hours report.\n");
    }

}
