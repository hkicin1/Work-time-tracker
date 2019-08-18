package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.User;

import java.io.IOException;

public class EmployeeController {

    private User registeredEmployee;

    public void setRegisteredEmployee(User a) {
        this.registeredEmployee = a;
    }

    public void selectProjectAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/selectProject.fxml"));
            root = loader.load();
            SelectProjectController controller = loader.getController();
            //controller.start();
            stage.setResizable(false);
            stage.setTitle("Select project");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startWorkTimeAction(ActionEvent actionEvent) {
    }
}
