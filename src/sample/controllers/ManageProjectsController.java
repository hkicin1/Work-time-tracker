package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.models.Project;
import sample.models.ProjectDAO;

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

    public void addProjectAction(ActionEvent actionEvent) {
    }

    public void deleteProjectAction(ActionEvent actionEvent) {
        /*Project project = tblEmployees.getSelectionModel().getSelectedItem();
        if (user == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Deleting user " + user.getName());
        alert.setContentText("Are you sure you want to delete user " + user.getName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            daoUser.removeUser(user);
            userListObs.setAll(daoUser.listUsers());
        }*/
    }
}
