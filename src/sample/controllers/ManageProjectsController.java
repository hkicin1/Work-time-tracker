package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.Project;
import sample.utilities.ProjectDAO;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ManageProjectsController {
    public Button btnEnd;
    public TableView<Project> tableProjects;
    public TableColumn nameCol;

    private Project project;
    private ProjectDAO daoProject;
    private ObservableList<Project> projectListObs;

    public ManageProjectsController() {
        daoProject = ProjectDAO.getInst();
        projectListObs = FXCollections.observableArrayList(daoProject.listProjects());
    }

    @FXML
    public void initialize() {
        tableProjects.setItems(projectListObs);
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));

        project = tableProjects.getSelectionModel().getSelectedItem();
    }

    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnEnd.getScene().getWindow();
        stage.close();
    }

    public void addProjectAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addProject.fxml"));
        root = loader.load();
        stage.setTitle("Adding new project");
        stage.setResizable(false);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void deleteProjectAction(ActionEvent actionEvent) {
        Project project = tableProjects.getSelectionModel().getSelectedItem();
        if (project == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Deleting project " + project.getName());
        alert.setContentText("Are you sure you want to delete project " + project.getName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            daoProject.removeProject(project);
            projectListObs.setAll(daoProject.listProjects());
        }
    }
}
