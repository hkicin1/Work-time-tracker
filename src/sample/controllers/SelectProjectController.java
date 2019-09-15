package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.Project;
import sample.utilities.ProjectDAO;
import sample.models.User;

import java.io.IOException;


public class SelectProjectController {
    public Button btnExit;
    public Button btnStart;
    public TableView tableList;
    public TableColumn nameCol;

    private ObservableList<Project> allProjectsObs = FXCollections.observableArrayList();
    public Project selectedProject;
    private ProjectDAO dao;

    private User registeredEmployee;


    public SelectProjectController(User registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
        dao = dao.getInst();
        allProjectsObs = FXCollections.observableArrayList(dao.listProjects());
    }

    @FXML
    public void initialize() {
        tableList.setItems(allProjectsObs);
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
    }

    public void startAction(ActionEvent actionEvent) {
        int index = tableList.getSelectionModel().getFocusedIndex();
        selectedProject = (Project) tableList.getItems().get(index);

        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startProjectTime.fxml"));
            StartProjectTimeController controller = new StartProjectTimeController(registeredEmployee, selectedProject);
            loader.setController(controller);
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

    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void setRegisteredEmployee(User registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
    }
}
