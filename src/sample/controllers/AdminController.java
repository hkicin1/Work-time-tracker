package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.User;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AdminController {
    public void setRegisteredAdmin(User a) {
    }

    public void manageEmployeesAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/manageEmployees.fxml"));
        root = loader.load();
        stage.setTitle("Manage employees");
        stage.setResizable(false);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void manageProjectsAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/manageProjects.fxml"));
        root = loader.load();
        stage.setTitle("Manage projects");
        stage.setResizable(false);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void helpAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminHelp.fxml"));
        root = loader.load();
        stage.setTitle("Help");
        stage.setResizable(false);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
