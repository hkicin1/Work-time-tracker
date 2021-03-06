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
        lblHelpAdmin.setText("By clicking on manage employees button, you will be able to add \n" +
                "new employee, delete another, or edit data.\n\n"
                + "By clicking on manage project button, you will be able to add \n" +
                "new project or delete existing.\n\n"
                + "By clicking on report buttons, you can see work hours and \n" +
                "project work hours report.\n");
    }

}
