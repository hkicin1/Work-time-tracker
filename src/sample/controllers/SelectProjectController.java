package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.Project;
import sample.utilities.WorkTimeTracker;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SelectProjectController implements Initializable {
    public Button btnExit;
    public Button btnStart;
    public ListView projectListView;

    private ObservableList<Project> allProjectsObs = FXCollections.observableArrayList();
    WorkTimeTracker dao = WorkTimeTracker.createWorkTimeTracker();


    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void start(){
        initialize(null, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<Project> tmpProjectList = dao.getAllProjects();
            allProjectsObs.addAll(tmpProjectList);

            projectListView.setItems(allProjectsObs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void startAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startProjectTime.fxml"));
            root = loader.load();
            stage.setResizable(false);
            stage.setTitle("Start project time");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
