package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.enums.ContentType;

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
        lblHelpAdmin.setText("By clicking on manage employees button, you will be able to add new employee, delete another, or edit data.\n"
                + "By clicking on manage project button, you will be able to add new project or delete existing.\n"
                + "By clicking on reports button, you will be able to see work hours report.\n");
    }

}
