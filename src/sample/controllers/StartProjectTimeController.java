package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class StartProjectTimeController {

    public Label currentProjectLabel;
    private SelectProjectController controller = new SelectProjectController();

    @FXML
    public void initialize() {
        currentProjectLabel.setText(controller.getSelectedProject().getName());
    }
    public void startAction(ActionEvent actionEvent) {


    }

    public void stopAction(ActionEvent actionEvent) {

    }
}
